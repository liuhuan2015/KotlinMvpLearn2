package com.liuh.kotlinmvplearn2.view.recyclerview.adapter

/**
 * Date: 2018/10/11 09:02
 * Description: Adapter条目的点击事件
 */
interface OnItemClickListener {

    fun onItemClick(obj: Any?, position: Int)
}