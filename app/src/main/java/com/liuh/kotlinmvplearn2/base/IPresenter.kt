package com.liuh.kotlinmvplearn2.base

interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}