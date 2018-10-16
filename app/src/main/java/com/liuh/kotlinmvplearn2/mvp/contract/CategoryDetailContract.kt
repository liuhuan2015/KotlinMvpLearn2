package com.liuh.kotlinmvplearn2.mvp.contract

import com.liuh.kotlinmvplearn2.base.IBaseView
import com.liuh.kotlinmvplearn2.base.IPresenter
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean

/**
 * Date: 2018/10/16 09:32
 * Description: 分类详情契约类
 */
interface CategoryDetailContract {

    interface View : IBaseView {

        /**
         * 设置列表数据
         */
        fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg: String)
    }

    interface Presenter : IPresenter<View> {

        fun getCategoryDetailList(id: Long)

        fun loadMoreData()
    }


}