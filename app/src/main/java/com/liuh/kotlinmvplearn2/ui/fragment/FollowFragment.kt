package com.liuh.kotlinmvplearn2.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment
import com.liuh.kotlinmvplearn2.mvp.contract.FollowContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.mvp.presenter.FollowPresenter
import com.liuh.kotlinmvplearn2.net.exception.ErrorStatus
import com.liuh.kotlinmvplearn2.showToast
import com.liuh.kotlinmvplearn2.ui.adapter.FollowAdapter
import kotlinx.android.synthetic.main.fragment_follow.*

/**
 * Date: 2018/10/15 14:23
 * Description: 发现 ---> 关注
 */
class FollowFragment : BaseFragment(), FollowContract.View {

    private var mTitle: String? = null

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private val mPresenter by lazy { FollowPresenter() }

    private val mFollowAdapter by lazy { FollowAdapter(activity as Activity, itemList) }

    // 是否加载更多
    private var loadingMore = false

    init {
        mPresenter.attachView(this)
    }

    companion object {
        fun getInstance(title: String): FollowFragment {
            val fragment = FollowFragment()
            val bundle = Bundle()

            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_follow
    }

    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mFollowAdapter

        // 实现自动加载
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val itemCount = mRecyclerView.layoutManager.itemCount
                val lastVisibleItemPos = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (!loadingMore && lastVisibleItemPos == (itemCount - 1)) {
                    loadingMore = true
                    mPresenter.loadMoreData()
                }
            }
        })
        mLayoutStatusView = multipleStatusView
    }

    override fun lazyLoad() {
        mPresenter.requestFollowList()
    }

    override fun setFollowInfo(issue: HomeBean.Issue) {
        loadingMore = false
        itemList = issue.itemList
        mFollowAdapter.addData(itemList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView.showContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}