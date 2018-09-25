package com.liuh.kotlinmvplearn2.mvp.presenter

import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.mvp.contract.HomeContract
import com.liuh.kotlinmvplearn2.mvp.model.HomeModel
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean

/**
 * 首页精选的 Presenter
 * （首页数据是 Banner 数据和一页数据组合而成的 HomeBean）
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {


    private var bannerHomeBean: HomeBean? = null

    private var nextPageUrl: String? = null

    private val homeModel: HomeModel by lazy {

        HomeModel()

    }

    /**
     * 获取首页精选数据（Banner + 一页数据）
     */
    override fun requestHomeData(num: Int) {

        // 检查是否绑定了 View
        checkViewAttached()

        mRootView?.showLoading()

//        val disposable=homeModel.




    }

    override fun loadMoreData() {

    }
}