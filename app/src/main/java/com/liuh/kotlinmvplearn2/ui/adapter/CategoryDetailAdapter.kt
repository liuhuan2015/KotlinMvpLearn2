package com.liuh.kotlinmvplearn2.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.liuh.kotlinmvplearn2.Constants
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.durationFormat
import com.liuh.kotlinmvplearn2.glide.GlideApp
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.ui.activity.VideoDetailActivity
import com.liuh.kotlinmvplearn2.view.recyclerview.MyViewHolder
import com.liuh.kotlinmvplearn2.view.recyclerview.adapter.CommonAdapter

/**
 * Date: 2018/10/16 14:15
 * Description:
 */
class CategoryDetailAdapter(context: Context, dataList: ArrayList<HomeBean.Issue.Item>, layoutId: Int)
    : CommonAdapter<HomeBean.Issue.Item>(context, dataList, layoutId) {

    fun addData(dataList: ArrayList<HomeBean.Issue.Item>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: MyViewHolder, data: HomeBean.Issue.Item, position: Int) {
        setVideoItem(holder, data)
    }

    private fun setVideoItem(holder: MyViewHolder, item: HomeBean.Issue.Item) {
        val itemData = item.data
        val cover = itemData?.cover?.feed ?: ""
        // 加载封页图
        GlideApp.with(mContext)
                .load(cover)
                .apply(RequestOptions().placeholder(R.drawable.placeholder_banner))
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_image))

        holder.setText(R.id.tv_title, itemData?.title ?: "")

        // 格式化时间
        val timeFormat = durationFormat(itemData?.duration)

        holder.setText(R.id.tv_tag, "#${itemData?.category}/$timeFormat")

        holder.setOnItemClickListener(listener = View.OnClickListener {
            gotoVideoPlayer(mContext as Activity, holder.getView(R.id.iv_image), item)
        })

    }

    /**
     * 跳转到视频详情页面播放
     */
    private fun gotoVideoPlayer(activity: Activity, view: View, itemData: HomeBean.Issue.Item) {
        val intent = Intent(activity, VideoDetailActivity::class.java)
        intent.putExtra(Constants.BUNDLE_VIDEO_DATA, itemData)
        intent.putExtra(VideoDetailActivity.TRANSITION, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, VideoDetailActivity.IMG_TRANSITION)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair)
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }
    }

}