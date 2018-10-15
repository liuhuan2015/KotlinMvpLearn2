package com.liuh.kotlinmvplearn2.mvp.presenter

import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.mvp.contract.FollowContract
import com.liuh.kotlinmvplearn2.mvp.model.FollowModel
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.net.exception.ExceptionHandler

/**
 * Date: 2018/10/15 14:51
 * Description:
 */
class FollowPresenter : BasePresenter<FollowContract.View>(), FollowContract.Presenter {

    private val followModel by lazy { FollowModel() }

    private var nextPageUrl: String? = null

    /**
     * 请求关注数据
     */
    override fun requestFollowList() {
        checkViewAttached()

        mRootView?.showLoading()

        val disposable = followModel.requestFollowList()
                .subscribe({ issue: HomeBean.Issue ->
                    mRootView?.apply {
                        dismissLoading()
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }
                }, { throwable: Throwable ->
                    // 处理异常
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandler.handleException(throwable), ExceptionHandler.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {
        val disposable = nextPageUrl?.let {
            followModel.loadMoreData(it)
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setFollowInfo(issue)
                        }
                    }, { t ->
                        mRootView?.apply {
                            showError(ExceptionHandler.handleException(t), ExceptionHandler.errorCode)
                        }
                    })
        }

        if (disposable != null) {
            addSubscription(disposable)
        }
    }

}