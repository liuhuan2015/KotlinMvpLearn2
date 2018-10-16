package com.liuh.kotlinmvplearn2.ui.adapter

import android.app.Activity
import com.liuh.kotlinmvplearn2.mvp.model.bean.CategoryBean
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.liuh.kotlinmvplearn2.Constants
import com.liuh.kotlinmvplearn2.MyApplication
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.glide.GlideApp
import com.liuh.kotlinmvplearn2.ui.activity.CategoryDetailActivity
import com.liuh.kotlinmvplearn2.view.recyclerview.MyViewHolder
import com.liuh.kotlinmvplearn2.view.recyclerview.adapter.CommonAdapter

/**
 * Date: 2018/10/15 18:16
 * Description:
 */
class CategoryAdapter(mContext: Context, categoryList: ArrayList<CategoryBean>, layoutId: Int) :
        CommonAdapter<CategoryBean>(mContext, categoryList, layoutId) {

    private var textTypeface: Typeface? = null

    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    /**
     * 设置新数据
     */
    fun setData(categoryList: ArrayList<CategoryBean>) {
        mData.clear()
        mData = categoryList
        notifyDataSetChanged()
    }


    override fun bindData(holder: MyViewHolder, data: CategoryBean, position: Int) {
        holder.setText(R.id.tv_category_name, "#${data.name}")

        // 设置方正兰亭细黑简体
        holder.getView<TextView>(R.id.tv_category_name).typeface = textTypeface

        holder.setImagePath(R.id.iv_category, object : MyViewHolder.HolderImageLoader(data.bgPicture) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                        .load(path)
                        .placeholder(R.color.color_darker_gray)
                        .transition(DrawableTransitionOptions().crossFade())
                        .thumbnail(0.5f)
                        .into(iv)
            }
        })

        holder.setOnItemClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(mContext as Activity, CategoryDetailActivity::class.java)
                intent.putExtra(Constants.BUNDLE_CATEGORY_DATA, data)
                mContext.startActivity(intent)
            }
        })
    }
}