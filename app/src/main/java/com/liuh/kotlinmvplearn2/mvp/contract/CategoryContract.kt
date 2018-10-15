package com.liuh.kotlinmvplearn2.mvp.contract

import com.liuh.kotlinmvplearn2.base.IBaseView
import com.liuh.kotlinmvplearn2.base.IPresenter
import com.liuh.kotlinmvplearn2.mvp.model.bean.CategoryBean

/**
 * Date: 2018/10/15 17:58
 * Description:
 */
interface CategoryContract {

    interface View : IBaseView {

        /**
         * 显示分类的信息
         */
        fun showCategory(categoryList: ArrayList<CategoryBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 获取分类的信息
         */
        fun getCategoryData()
    }

}