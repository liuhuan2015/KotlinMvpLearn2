package com.liuh.kotlinmvplearn2.mvp.contract

import com.liuh.kotlinmvplearn2.base.IBaseView
import com.liuh.kotlinmvplearn2.base.IPresenter
import com.liuh.kotlinmvplearn2.mvp.model.bean.TabInfoBean

/**
 * Date: 2018/10/16 10:53
 * Description:
 */
interface HotTabContract {

    interface View : IBaseView {

        /**
         * 设置 TabInfo
         */
        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg: String, errorCode: Int)

    }

    interface Presenter : IPresenter<View> {

        /**
         * 获取 TabInfo
         */
        fun getTabInfo()
    }

}