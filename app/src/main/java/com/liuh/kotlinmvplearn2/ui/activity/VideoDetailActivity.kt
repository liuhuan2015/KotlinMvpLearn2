package com.liuh.kotlinmvplearn2.ui.activity

import android.annotation.SuppressLint
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseActivity
import com.liuh.kotlinmvplearn2.mvp.contract.VideoDetailContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.mvp.presenter.VideoDetailPresenter
import com.liuh.kotlinmvplearn2.ui.adapter.VideoDetailAdapter
import java.text.SimpleDateFormat

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


    override fun layoutId(): Int = R.layout.activity_video_detail

    override fun initData() {

    }

    override fun initView() {
        mPresenter.attachView(this)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun setVideoUrl(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVideoInfo(itemInfo: HomeBean.Issue.Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBackground(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRecentRelatedVideo(itemList: ArrayList<HomeBean.Issue.Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setErrorMsg(errorMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}