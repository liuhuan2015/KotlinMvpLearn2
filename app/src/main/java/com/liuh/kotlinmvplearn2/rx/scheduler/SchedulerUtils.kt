package com.liuh.kotlinmvplearn2.rx.scheduler

/**
 * Date: 2018/9/25 09:39
 * Description:
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }

}