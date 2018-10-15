package com.liuh.kotlinmvplearn2.mvp.presenter

import android.app.Activity
import com.liuh.kotlinmvplearn2.DisplayManager
import com.liuh.kotlinmvplearn2.MyApplication
import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.dataFormat
import com.liuh.kotlinmvplearn2.mvp.contract.VideoDetailContract
import com.liuh.kotlinmvplearn2.mvp.model.VideoDetailModel
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.net.exception.ExceptionHandler
import com.liuh.kotlinmvplearn2.showToast
import com.liuh.kotlinmvplearn2.utils.NetworkUtil

/**
 * Date: 2018/10/12 16:59
 * Description:
 */
class VideoDetailPresenter : BasePresenter<VideoDetailContract.View>(), VideoDetailContract.Presenter {

    private val videoDetailModel: VideoDetailModel by lazy {
        VideoDetailModel()
    }

    /**
     * 加载视频相关的数据
     */
    override fun loadVideoInfo(itemInfo: HomeBean.Issue.Item) {
        val playInfo = itemInfo.data?.playInfo

        val netType = NetworkUtil.isWifi(MyApplication.context)

        // 检测是否绑定了 View
        checkViewAttached()
        if (playInfo!!.size > 1) {
            // 当前网络环境是wifi，选择高清的视频
            if (netType) {
                for (i in playInfo) {
                    if (i.type == "high") {
                        val playUrl = i.url
                        mRootView?.setVideoUrl(playUrl)
                    }
                }
            } else {
                // 否则就选择标清的视频
                for (i in playInfo) {
                    if (i.type == "normal") {
                        val playUrl = i.url
                        mRootView?.setVideoUrl(playUrl)

                        // TODO 待完善
                        (mRootView as Activity).showToast("本次消耗${(mRootView as Activity).dataFormat(i.urlList[0].size)}流量")
                    }
                }
            }
        } else {
            mRootView?.setVideoUrl(itemInfo.data.playUrl)
        }

        // 设置背景
        val backgroundUrl = itemInfo.data.cover.blurred + "/thumbnail/${DisplayManager.getScreenHeight()!! - DisplayManager.dip2px(250f)!!}x${DisplayManager.getScreenWidth()}"
        backgroundUrl.let { mRootView?.setBackground(it) }

        mRootView?.setVideoInfo(itemInfo)
    }

    override fun requestRelatedVideo(id: Long) {
        mRootView?.showLoading()

        val disposable = videoDetailModel.requestRelatedData(id)
                .subscribe({ t: HomeBean.Issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRecentRelatedVideo(t.itemList)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        setErrorMsg(ExceptionHandler.handleException(t))
                    }
                })

        addSubscription(disposable)
    }
}