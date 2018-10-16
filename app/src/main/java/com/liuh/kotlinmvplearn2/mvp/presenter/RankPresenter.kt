package com.liuh.kotlinmvplearn2.mvp.presenter

import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.mvp.contract.RankContract
import com.liuh.kotlinmvplearn2.mvp.model.RankModel
import com.liuh.kotlinmvplearn2.net.exception.ExceptionHandler

/**
 * Date: 2018/10/16 14:04
 * Description:
 */
class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {

    private val rankModel by lazy { RankModel() }

    /**
     * 请求排行榜数据
     */
    override fun requestRankList(apiUrl: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = rankModel.requestRankList(apiUrl)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRankList(issue.itemList)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandler.handleException(t), ExceptionHandler.errorCode)
                    }
                })

        addSubscription(disposable)
    }

}