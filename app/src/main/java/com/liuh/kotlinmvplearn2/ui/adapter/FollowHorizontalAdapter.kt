package com.liuh.kotlinmvplearn2.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.liuh.kotlinmvplearn2.Constants
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.durationFormat
import com.liuh.kotlinmvplearn2.glide.GlideApp
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.ui.activity.VideoDetailActivity
import com.liuh.kotlinmvplearn2.view.recyclerview.MyViewHolder
import com.liuh.kotlinmvplearn2.view.recyclerview.adapter.CommonAdapter
import android.support.v4.util.Pair

/**
 * Date: 2018/10/15 15:27
 * Description: 发现 ---> 关注 ---> 横向的数据适配器
 */
class FollowHorizontalAdapter(mContext: Context, categoryList: ArrayList<HomeBean.Issue.Item>, layoutId: Int) :
        CommonAdapter<HomeBean.Issue.Item>(mContext, categoryList, layoutId) {

    override fun bindData(holder: MyViewHolder, data: HomeBean.Issue.Item, position: Int) {
        val horizontalItemData = data.data

        holder.setImagePath(R.id.iv_cover_feed, object : MyViewHolder.HolderImageLoader(data.data?.cover?.feed!!) {
            override fun loadImage(iv: ImageView, path: String) {
                // 加载封页图
                GlideApp.with(mContext)
                        .load(path)
                        .placeholder(R.drawable.placeholder_banner)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(holder.getView(R.id.iv_cover_feed))
            }
        })

        // 横向 RecyclerView 封页图下面标题
        holder.setText(R.id.tv_title, horizontalItemData?.title ?: "")

        // 格式化时间
        val timeFormat = durationFormat(horizontalItemData?.duration)

        // 标签
        with(holder) {
            if (horizontalItemData?.tags != null && horizontalItemData.tags.size > 0) {
                setText(R.id.tv_tag, "#${horizontalItemData.tags[0].name}/$timeFormat")
            } else {
                setText(R.id.tv_tag, "#$timeFormat")
            }

            setOnItemClickListener(listener = View.OnClickListener {
                gotoVideoPlayer(mContext as Activity, holder.getView(R.id.iv_cover_feed), data)
            })
        }
    }

    /**
     * 跳转到视频详情页面
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