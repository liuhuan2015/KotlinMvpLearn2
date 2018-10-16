package com.liuh.kotlinmvplearn2.mvp.presenter

import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.mvp.contract.CategoryContract
import com.liuh.kotlinmvplearn2.mvp.model.CategoryModel
import com.liuh.kotlinmvplearn2.mvp.model.bean.CategoryBean
import com.liuh.kotlinmvplearn2.net.exception.ExceptionHandler

/**
 * Date: 2018/10/15 18:16
 * Description:
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }

    override fun getCategoryData() {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = categoryModel.getCategoryData()
                .subscribe({ categoryList: ArrayList<CategoryBean>? ->
                    mRootView?.apply {
                        dismissLoading()
                        showCategory(categoryList!!)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        // 处理异常
                        showError(ExceptionHandler.handleException(t), ExceptionHandler.errorCode)
                    }
                })

        addSubscription(disposable)
    }

}