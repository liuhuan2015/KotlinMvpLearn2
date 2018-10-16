package com.liuh.kotlinmvplearn2.mvp.model

import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.net.RetrofitManager
import com.liuh.kotlinmvplearn2.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Date: 2018/10/16 14:07
 * Description: 排行榜 model
 */
class RankModel {

    /**
     * 获取排行榜
     */
    fun requestRankList(apiUrl: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(apiUrl)
                .compose(SchedulerUtils.ioToMain())
    }

}