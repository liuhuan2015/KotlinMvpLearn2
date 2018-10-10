package com.liuh.kotlinmvplearn2.view.recyclerview

/**
 * Date: 2018/10/10 11:50
 * Description: 多布局条目类型
 */
interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}