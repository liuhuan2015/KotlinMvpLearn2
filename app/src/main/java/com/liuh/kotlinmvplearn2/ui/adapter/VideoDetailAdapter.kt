package com.liuh.kotlinmvplearn2.ui.adapter

import android.content.Context
import android.graphics.Typeface
import com.liuh.kotlinmvplearn2.MyApplication
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.view.recyclerview.MultipleType
import com.liuh.kotlinmvplearn2.view.recyclerview.MyViewHolder
import com.liuh.kotlinmvplearn2.view.recyclerview.adapter.CommonAdapter

/**
 * Date: 2018/10/12 17:00
 * Description:
 */
class VideoDetailAdapter(mContext: Context, data: ArrayList<HomeBean.Issue.Item>) :
        CommonAdapter<HomeBean.Issue.Item>(mContext, data, object : MultipleType<HomeBean.Issue.Item> {
            override fun getLayoutId(item: HomeBean.Issue.Item, position: Int): Int {

                return when {
                    position == 0 ->
                        R.layout.item_video_detail_info

                    data[position].type == "textCard" ->
                        R.layout.item_video_text_card

                    data[position].type == "textSmallCard" ->
                        R.layout.item_video_small_card

                    else ->
                        throw IllegalAccessException("Api 解析出错了，出现了其它类型")
                }
            }
        }) {

    private var textTypeface: Typeface? = null

    init {
        textTypeface=Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun bindData(holder: MyViewHolder, data: HomeBean.Issue.Item, position: Int) {



    }
}