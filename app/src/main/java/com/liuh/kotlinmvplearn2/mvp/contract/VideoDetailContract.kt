package com.liuh.kotlinmvplearn2.mvp.contract

import com.liuh.kotlinmvplearn2.base.IBaseView
import com.liuh.kotlinmvplearn2.base.IPresenter
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean

/**
 * Date: 2018/10/12 16:42
 * Description: 视频详情契约类
 */
interface VideoDetailContract {

    interface View : IBaseView {

        /**
         * 设置视频播放源
         */
        fun setVideoUrl(url: String)

        /**
         * 设置视频信息
         */
        fun setVideoInfo(itemInfo: HomeBean.Issue.Item)

        /**
         * 设置背景
         */
        fun setBackground(url: String)

        /**
         * 设置最新相关视频
         */
        fun setRecentRelatedVideo(itemList: ArrayList<HomeBean.Issue.Item>)

        /**
         * 设置错误信息
         */
        fun setErrorMsg(errorMsg: String)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 加载视频信息
         */
        fun loadVideoInfo(itemInfo: HomeBean.Issue.Item)

        /**
         * 请求相关的视频数据
         */
        fun requestRelatedVideo(id: Long)

    }

}