package com.liuh.kotlinmvplearn2.mvp.model

import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.net.RetrofitManager
import com.liuh.kotlinmvplearn2.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 * Date: 2018/10/15 17:00
 * Description: 关注Model
 */
class FollowModel {

    /**
     * 获取关注数据
     */
    fun requestFollowList(): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getFollowInfo()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }

}