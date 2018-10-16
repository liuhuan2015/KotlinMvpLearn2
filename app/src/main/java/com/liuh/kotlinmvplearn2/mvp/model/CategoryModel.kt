package com.liuh.kotlinmvplearn2.mvp.model

import com.liuh.kotlinmvplearn2.mvp.model.bean.CategoryBean
import com.liuh.kotlinmvplearn2.net.RetrofitManager
import com.liuh.kotlinmvplearn2.rx.scheduler.SchedulerUtils
import io.reactivex.Observable


/**
 * Date: 2018/10/16 09:55
 * Description: 分类数据模型
 */
class CategoryModel {

    /**
     * 获取分类信息
     */
    fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCategory()
                .compose(SchedulerUtils.ioToMain())
    }

}