package com.liuh.kotlinmvplearn2.mvp.model

import com.liuh.kotlinmvplearn2.mvp.model.bean.TabInfoBean
import com.liuh.kotlinmvplearn2.net.RetrofitManager
import com.liuh.kotlinmvplearn2.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Date: 2018/10/16 11:16
 * Description:
 */
class HotTabModel {

    /**
     * 获取 TabInfo
     */
    fun getTabInfo(): Observable<TabInfoBean> {
        return RetrofitManager.service.getRankList()
                .compose(SchedulerUtils.ioToMain())
    }
}