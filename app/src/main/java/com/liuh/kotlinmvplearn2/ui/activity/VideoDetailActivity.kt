package com.liuh.kotlinmvplearn2.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.res.Configuration
import android.os.Build
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.transition.Transition
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.liuh.kotlinmvplearn2.Constants
import com.liuh.kotlinmvplearn2.MyApplication
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseActivity
import com.liuh.kotlinmvplearn2.glide.GlideApp
import com.liuh.kotlinmvplearn2.mvp.contract.VideoDetailContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.mvp.presenter.VideoDetailPresenter
import com.liuh.kotlinmvplearn2.showToast
import com.liuh.kotlinmvplearn2.ui.adapter.VideoDetailAdapter
import com.liuh.kotlinmvplearn2.utils.CleanLeakUtils
import com.liuh.kotlinmvplearn2.utils.StatusBarUtil
import com.liuh.kotlinmvplearn2.utils.WatchHistoryUtils
import com.liuh.kotlinmvplearn2.view.VideoListener
import com.scwang.smartrefresh.header.MaterialHeader
import com.shuyu.gsyvideoplayer.listener.LockClickListener
import java.text.SimpleDateFormat
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.activity_video_detail.*
import java.util.*
import java.util.logging.Logger

@SuppressLint("SimpleDateFormat")
/**
 * Date: 2018/10/12 15:58
 * Description:
 */
class VideoDetailActivity : BaseActivity(), VideoDetailContract.View {

    companion object {
        val IMG_TRANSITION = "IMG_TRANSITION"
        val TRANSITION = "TRANSITION"
    }

    /**
     * 第一次调用的时候初始化
     */
    private val mPresenter by lazy { VideoDetailPresenter() }

    private val mAdapter by lazy { VideoDetailAdapter(this, itemList) }

    private val mFormat by lazy { SimpleDateFormat("yyyyMMddHHmmss") }


    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private lateinit var itemData: HomeBean.Issue.Item

    private var orientationUtils: OrientationUtils? = null

    private var isPlay: Boolean = false
    private var isPause: Boolean = false

    private var isTransition: Boolean = false

    private var transition: Transition? = null

    private var mMaterialHeader: MaterialHeader? = null

    override fun layoutId(): Int = R.layout.activity_video_detail

    /**
     * 初始化数据
     */
    override fun initData() {

        itemData = intent.getSerializableExtra(Constants.BUNDLE_VIDEO_DATA) as HomeBean.Issue.Item
        isTransition = intent.getBooleanExtra(TRANSITION, false)

        saveWatchVideoHistoryInfo(itemData)
    }

    /**
     * 保存观看记录
     */
    private fun saveWatchVideoHistoryInfo(watchItem: HomeBean.Issue.Item) {
        // 保存之前先检查sp中是否有该value的记录，有则删除，这样保证搜索历史记录不会有重复条目
        val historyMap = WatchHistoryUtils.getAll(Constants.FILE_WATCH_HISTORY_NAME, MyApplication.context) as Map<*, *>

        for ((key, _) in historyMap) {
            if (watchItem == WatchHistoryUtils.getObject(Constants.FILE_WATCH_HISTORY_NAME, MyApplication.context, key as String)) {
                WatchHistoryUtils.remove(Constants.FILE_WATCH_HISTORY_NAME, MyApplication.context, key)
            }
        }
        WatchHistoryUtils.putObject(Constants.FILE_WATCH_HISTORY_NAME, MyApplication.context, watchItem, "" + mFormat.format(Date()))
    }

    override fun initView() {
        mPresenter.attachView(this)
        // 过渡动画
        initTransition()

        initVideoViewConfig()

        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mRecyclerView.adapter = mAdapter

        // 设置相关视频 Item 的点击事件
        mAdapter.setOnItemDetailClick { mPresenter.loadVideoInfo(it) }

        // 状态栏透明和间距处理
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, mVideoView)

        // 下拉刷新  内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout?.setOnRefreshListener {
            loadVideoInfo()
        }

        mMaterialHeader = mRefreshLayout.refreshHeader as MaterialHeader
        // 打开下拉刷新区域块背景
        mMaterialHeader?.setShowBezierWave(true)
        // 设置下拉刷新主题颜色
        mRefreshLayout.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)
    }

    private fun initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition()
            ViewCompat.setTransitionName(mVideoView, IMG_TRANSITION)
            addTransitionListener()
            startPostponedEnterTransition()
        } else {
            loadVideoInfo()
        }
    }

    /**
     * 初始化 VideoView 的配置
     */
    private fun initVideoViewConfig() {

        // 设置旋转
        orientationUtils = OrientationUtils(this, mVideoView)

        // 是否旋转
        mVideoView.isRotateViewAuto = false

        // 是否可以滑动调整
        mVideoView.setIsTouchWiget(true)

        // 增加封面
        val imageView = ImageView(this)

        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        GlideApp.with(this)
                .load(itemData.data?.cover?.feed)
                .centerCrop()
                .into(imageView)

        mVideoView.thumbImageView = imageView

        mVideoView.setStandardVideoAllCallBack(object : VideoListener {

            override fun onPrepared(url: String, vararg objects: Any) {
                super.onPrepared(url, *objects)

                // 开始播放了才能旋转和全屏
                orientationUtils?.isEnable = true
                isPlay = true
            }

            override fun onAutoComplete(url: String, vararg objects: Any) {
                super.onAutoComplete(url, *objects)
                Log.e("-----", "onAutoComplete---自动播放完成")
            }

            override fun onPlayError(url: String, vararg objects: Any) {
                super.onPlayError(url, *objects)
                showToast("播放失败")
            }

            override fun onEnterFullscreen(url: String, vararg objects: Any) {
                super.onEnterFullscreen(url, *objects)
                Log.e("-----", "---进入全屏")
            }

            override fun onQuitFullscreen(url: String, vararg objects: Any) {
                super.onQuitFullscreen(url, *objects)
                Log.e("-----", "退出全屏")
                // 列表返回的样式判断
                orientationUtils?.backToProtVideo()
            }
        })

        // 设置返回按键功能
        mVideoView.backButton.setOnClickListener({ onBackPressed() })

        // 设置全屏按键功能
        mVideoView.fullscreenButton.setOnClickListener {
            // 直接横屏
            orientationUtils?.resolveByClick()
            //  是否有actionBar，有的话需要隐藏； 是否有statusBar，有的话需要隐藏
            mVideoView.startWindowFullscreen(this, true, true)
        }

        // 锁屏事件
        mVideoView.setLockClickListener(object : LockClickListener {
            override fun onClick(view: View?, lock: Boolean) {
                orientationUtils?.isEnable = !lock
            }
        })

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addTransitionListener() {
        transition = window.sharedElementEnterTransition
        transition?.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                Log.e("-----", "onTransitionEnd")

                loadVideoInfo()
                transition?.removeListener(this)
            }

            override fun onTransitionResume(transition: Transition?) {

            }

            override fun onTransitionPause(transition: Transition?) {

            }

            override fun onTransitionCancel(transition: Transition?) {

            }

            override fun onTransitionStart(transition: Transition?) {

            }
        })
    }

    /**
     * 加载视频信息
     */
    private fun loadVideoInfo() {
        mPresenter.loadVideoInfo(itemData)
    }

    override fun start() {

    }

    /**
     * 设置播放视频 url
     */
    override fun setVideoUrl(url: String) {
        Log.e("-----", "playUrl:$url")
        mVideoView.setUp(url, false, "")
        // 开始自动播放
        mVideoView.startPlayLogic()
    }

    /**
     * 设置视频信息
     */
    override fun setVideoInfo(itemInfo: HomeBean.Issue.Item) {
        itemData = itemInfo
        mAdapter.addData(itemInfo)
        // 请求相关的最新的视频
        mPresenter.requestRelatedVideo(itemInfo.data?.id ?: 0)
    }

    /**
     * 设置背景颜色
     */
    override fun setBackground(url: String) {
        GlideApp.with(this)
                .load(url)
                .centerCrop()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .transition(DrawableTransitionOptions().crossFade())
                .into(mVideoBackground)
    }

    override fun setRecentRelatedVideo(itemList: ArrayList<HomeBean.Issue.Item>) {
        mAdapter.addData(itemList)
        this.itemList = itemList
    }

    override fun setErrorMsg(errorMsg: String) {

    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
        mRefreshLayout.finishRefresh()
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && !isPause) {
            mVideoView.onConfigurationChanged(this, newConfig, orientationUtils)
        }
    }

    /**
     * 监听返回键
     */
    override fun onBackPressed() {
        orientationUtils?.backToProtVideo()
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return
        }

        // 释放所有
        mVideoView.setStandardVideoAllCallBack(null)

        GSYVideoPlayer.releaseAllVideos()

        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) run { super.onBackPressed() }
        else {
            finish()
            overridePendingTransition(R.anim.anim_out, R.anim.anim_in)
        }
    }

    override fun onResume() {
        super.onResume()
        getCurPlay().onVideoResume()
        isPause = false
    }

    override fun onPause() {
        super.onPause()
        getCurPlay().onVideoPause()
        isPause = true
    }

    override fun onDestroy() {
        CleanLeakUtils.fixInputMethodManagerLeak(this)
        super.onDestroy()
        GSYVideoPlayer.releaseAllVideos()
        orientationUtils?.releaseListener()
        mPresenter.detachView()
    }

    private fun getCurPlay(): GSYVideoPlayer {
        return if (mVideoView.fullWindowPlayer != null) {
            mVideoView.fullWindowPlayer
        } else mVideoView
    }




}





















