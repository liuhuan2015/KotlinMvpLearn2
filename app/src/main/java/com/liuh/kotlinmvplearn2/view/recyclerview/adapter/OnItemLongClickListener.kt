package com.liuh.kotlinmvplearn2.view.recyclerview.adapter

/**
 * Date: 2018/10/11 09:08
 * Description: Adapter条目的长按事件
 */
interface OnItemLongClickListener {

    fun onItemLongClick(obj: Any?, position: Int): Boolean
}