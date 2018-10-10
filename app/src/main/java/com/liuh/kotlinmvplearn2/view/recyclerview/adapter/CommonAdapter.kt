package com.liuh.kotlinmvplearn2.view.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.liuh.kotlinmvplearn2.view.recyclerview.MultipleType
import com.liuh.kotlinmvplearn2.view.recyclerview.MyViewHolder

/**
 * Date: 2018/10/10 11:33
 * Description: 通用的 Adapter
 */
abstract class CommonAdapter<T>(var mContext: Context, var mData: ArrayList<T>, // 条目布局
                                private var mLayoutId: Int) : RecyclerView.Adapter<MyViewHolder>() {

    protected var mInflater: LayoutInflater? = null
    private var mTypeSupport: MultipleType<T>? = null




}