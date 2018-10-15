package com.liuh.kotlinmvplearn2.mvp.model

import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.net.RetrofitManager
import com.liuh.kotlinmvplearn2.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Date: 2018/10/15 10:34
 * Description:
 */
class VideoDetailModel {

    fun requestRelatedData(id: Long): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getRelatedData(id).compose(SchedulerUtils.ioToMain())
    }
}