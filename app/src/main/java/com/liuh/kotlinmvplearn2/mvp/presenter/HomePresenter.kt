package com.liuh.kotlinmvplearn2.mvp.presenter

import com.liuh.kotlinmvplearn2.base.BasePresenter
import com.liuh.kotlinmvplearn2.mvp.contract.HomeContract
import com.liuh.kotlinmvplearn2.mvp.model.HomeModel
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.net.exception.ExceptionHandler

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

        val disposable = homeModel.requestHomeData(num)
                .flatMap { homeBean ->

                    // 过滤掉 Banner2（包含广告等不需要的type），具体见接口分析
                    val bannerItemList = homeBean.issueList[0].itemList

                    bannerItemList.filter { item ->
                        item.type == "banner" || item.type == "horizontalScrollCard"
                    }.forEach { item ->
                        bannerItemList.remove(item)
                    }

                    bannerHomeBean = homeBean // 记录第一页是当做 banner 数据

                    // 根据 nextPageUrl 请求下一页数据
                    homeModel.loadMoreData(homeBean.nextPageUrl)
                }

                .subscribe({ homeBean ->
                    mRootView?.apply {
                        dismissLoading()

                        nextPageUrl = homeBean.nextPageUrl

                        val newBannerItemList = homeBean.issueList[0].itemList

                        newBannerItemList.filter { item ->
                            item.type == "banner2" || item.type == "horizontalScrollCard"
                        }.forEach { item ->
                            newBannerItemList.remove(item)
                        }

                        // 重新赋值 Banner 长度
                        bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size

                        // 赋值过滤后的数据 + banner数据
                        bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)

                        setHomeData(bannerHomeBean!!)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandler.handleException(t), ExceptionHandler.errorCode)
                    }
                })

        addSubscription(disposable)
    }


    /**
     * 加载更多
     */
    override fun loadMoreData() {
        val disposable = nextPageUrl?.let {
            homeModel.loadMoreData(it)
                    .subscribe({ homeBean ->
                        mRootView?.apply {

                            // 过滤
                            val newItemList = homeBean.issueList[0].itemList

                            newItemList.filter { item ->
                                item.type == "banner2" || item.type == "horizontalScrollCard"
                            }.forEach { item ->
                                newItemList.remove(item)
                            }

                            nextPageUrl = homeBean.nextPageUrl
                            setMoreData(newItemList)

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