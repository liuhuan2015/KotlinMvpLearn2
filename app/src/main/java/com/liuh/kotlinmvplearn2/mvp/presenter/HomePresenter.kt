package com.liuh.kotlinmvplearn2.mvp.presenter

import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.mvp.contract.HomeContract

/**
 * 首页精选的 Presenter
 * （首页数据是 Banner 数据和一页数据组合而成的 HomeBean）
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {





    override fun requestHomeData(num: Int) {

    }

    override fun loadMoreData() {

    }
}