package com.liuh.kotlinmvplearn2.mvp.model

import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.net.RetrofitManager
import com.liuh.kotlinmvplearn2.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Date: 2018/9/25 08:44
 * Description: 首页精选 model
 */
class HomeModel {

    /**
     * 获取首页数据
     */
    fun requestHomeData(num: Int): Observable<HomeBean> {
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url: String): Observable<HomeBean> {
        return RetrofitManager.service.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }

}