package com.liuh.kotlinmvplearn2.mvp.presenter

import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.mvp.contract.VideoDetailContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean

/**
 * Date: 2018/10/12 16:59
 * Description:
 */
class VideoDetailPresenter : BasePresenter<VideoDetailContract.View>(), VideoDetailContract.Presenter {

    override fun loadVideoInfo(itemInfo: HomeBean.Issue.Item) {

    }

    override fun requestRelatedVideo(id: Long) {

    }
}