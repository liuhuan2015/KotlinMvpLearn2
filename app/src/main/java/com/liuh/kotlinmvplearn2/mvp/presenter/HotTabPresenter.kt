package com.liuh.kotlinmvplearn2.mvp.presenter

import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.mvp.contract.HotTabContract
import com.liuh.kotlinmvplearn2.mvp.model.HotTabModel
import com.liuh.kotlinmvplearn2.net.exception.ExceptionHandler

/**
 * Date: 2018/10/16 11:14
 * Description:
 */
class HotTabPresenter : BasePresenter<HotTabContract.View>(), HotTabContract.Presenter {

    private val hotTabModel by lazy { HotTabModel() }

    override fun getTabInfo() {
        checkViewAttached()

        val disposable = hotTabModel.getTabInfo()
                .subscribe({ tabInfo ->
                    mRootView?.setTabInfo(tabInfo)
                }, { throwable ->
                    // 处理异常
                    mRootView?.showError(ExceptionHandler.handleException(throwable), ExceptionHandler.errorCode)
                })

        addSubscription(disposable)
    }
}