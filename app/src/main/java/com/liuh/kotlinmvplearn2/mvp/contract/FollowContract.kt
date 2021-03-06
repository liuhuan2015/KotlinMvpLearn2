package com.liuh.kotlinmvplearn2.mvp.contract

import com.liuh.kotlinmvplearn2.base.IBaseView
import com.liuh.kotlinmvplearn2.base.IPresenter
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean

/**
 * Date: 2018/10/15 14:54
 * Description:
 */
interface FollowContract {

    interface View : IBaseView {

        /**
         * 设置关注信息数据
         */
        fun setFollowInfo(issue: HomeBean.Issue)

        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 获取 List
         */
        fun requestFollowList()

        /**
         * 加载更多
         */
        fun loadMoreData()
    }

}