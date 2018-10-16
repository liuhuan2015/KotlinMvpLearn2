package com.liuh.kotlinmvplearn2.mvp.contract

import com.liuh.kotlinmvplearn2.base.IBaseView
import com.liuh.kotlinmvplearn2.base.IPresenter
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean

/**
 * Date: 2018/10/16 11:36
 * Description: 契约类
 */
interface RankContract {

    interface View : IBaseView {

        /**
         * 设置排行榜的数据
         */
        fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 获取 TabInfo
         */
        fun requestRankList(apiUrl: String)
    }

}