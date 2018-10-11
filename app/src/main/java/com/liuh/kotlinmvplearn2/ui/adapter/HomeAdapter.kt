package com.liuh.kotlinmvplearn2.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.view.recyclerview.MyViewHolder
import com.liuh.kotlinmvplearn2.view.recyclerview.adapter.CommonAdapter
import io.reactivex.Observable

/**
 * Date: 2018/10/10 11:29
 * Description: 首页精选的Adapter
 */
class HomeAdapter(context: Context, data: ArrayList<HomeBean.Issue.Item>)
    : CommonAdapter<HomeBean.Issue.Item>(context, data, -1) {

    // banner 作为 RecyclerView 的第一项
    var bannerItemSize = 0

    companion object {
        private val ITEM_TYPE_BANNER = 1  // Banner 类型
        private val ITEM_TYPE_TEXT_HEADER = 2  // textHeader
        private val ITEM_TYPE_CONTENT = 3  // item
    }

    /**
     * 设置 Banner 大小
     */
    fun setBannerSize(count: Int) {
        bannerItemSize = count
    }

    /**
     * 添加更多数据
     */
    fun addItemData(itemList: ArrayList<HomeBean.Issue.Item>) {
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }

    /**
     * 得到 Item 的类型
     */
    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> ITEM_TYPE_BANNER
            mData[position + bannerItemSize - 1].type == "textHeader" -> ITEM_TYPE_TEXT_HEADER
            else -> ITEM_TYPE_CONTENT
        }
    }

    /**
     * 创建布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return when (viewType) {
            ITEM_TYPE_BANNER -> MyViewHolder(mInflater!!.inflate(R.layout.item_home_banner, parent))
            ITEM_TYPE_TEXT_HEADER -> MyViewHolder(mInflater!!.inflate(R.layout.item_home_header, parent))
            else -> MyViewHolder(mInflater!!.inflate(R.layout.item_home_content, parent))
        }
    }

    /**
     * 得到 RecyclerView Item 数量（Banner 作为一个 item）
     */
    override fun getItemCount(): Int {
        return when {
            mData.size > bannerItemSize -> mData.size - bannerItemSize + 1
            mData.isEmpty() -> 0
            else -> 1
        }
    }

    /**
     * 绑定布局
     */
    override fun bindData(holder: MyViewHolder, data: HomeBean.Issue.Item, position: Int) {
        when (getItemViewType(position)) {

            ITEM_TYPE_BANNER -> {
                val bannerItemData: ArrayList<HomeBean.Issue.Item> = mData.take(bannerItemSize).toCollection(ArrayList())
                val bannerFeedList = ArrayList<String>()
                val bannerTitleList = ArrayList<String>()

                // 取出banner 显示的 img 和title
                Observable.fromIterable(bannerItemData)
                        .subscribe({ list ->
                            bannerFeedList.add(list.data?.cover?.feed ?: "")
                            bannerTitleList.add(list.data?.title ?: "")
                        })

                // 设置banner
                with(holder) {
                    getView<BGABanner>(R.id.banner).run {
                        setAutoPlayAble(bannerFeedList.size > 1) // 设置自动轮播
                        setData(bannerFeedList, bannerTitleList)
                        setAdapter(object : BGABanner.Adapter<ImageView, String> {
                            override fun fillBannerItem(banner: BGABanner?, imageView: ImageView?, feedImageUrl: String?, position: Int) {
                                GlideApp.with(mContext)
                                        .load(feedImageUrl)
                                        .transition(DrawableTransitionOptions().crossFade())
                                        .placeholder(R.drawable.placeholder_banner)
                                        .into(imageView)
                            }
                        })
                    }
                }

                // 没有使用到的参数在 kotlin 中用"_"代替
                holder.getView<BGABanner>(R.id.banner).setDelegate { _, imageView, _, i ->
                    goToVideoPlayer(mContext as Activity, imageView, bannerItemData[i])
                }

            }

            ITEM_TYPE_TEXT_HEADER -> {
                holder.setText(R.id.tvHeader, mData[position + bannerItemSize - 1].data?.text ?: "")
            }

            ITEM_TYPE_CONTENT -> {
                setVideoItem(holder, mData[position + bannerItemSize - 1])
            }
        }
    }

    // 跳到视频播放详情页
    private fun goToVideoPlayer(activity: Activity, imageView: View?, item: HomeBean.Issue.Item) {
        // TODO
    }

    // 加载 content item
    private fun setVideoItem(holder: MyViewHolder, item: HomeBean.Issue.Item) {
        // TODO
    }

}
















