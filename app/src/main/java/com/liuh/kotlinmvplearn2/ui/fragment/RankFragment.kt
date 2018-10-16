package com.liuh.kotlinmvplearn2.ui.fragment

import android.app.Activity
import android.os.Bundle
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment
import com.liuh.kotlinmvplearn2.mvp.contract.RankContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.mvp.presenter.RankPresenter
import com.liuh.kotlinmvplearn2.ui.adapter.CategoryDetailAdapter

/**
 * Date: 2018/10/16 11:35
 * Description:
 */
class RankFragment : BaseFragment(), RankContract.View {

    private val mPresenter by lazy { RankPresenter() }

    private val mAdapter by lazy { CategoryDetailAdapter(activity as Activity, itemList, R.layout.item_category_detail) }

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private var apiUrl: String? = null

    companion object {
        fun getInstance(apiUrl: String): RankFragment {
            val fragment = RankFragment()
            val bundle = Bundle()

            fragment.arguments = bundle
            fragment.apiUrl = apiUrl
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }


    override fun getLayoutId(): Int = R.layout.fragment_rank

    override fun initView() {

    }

    override fun lazyLoad() {

    }

    override fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>) {

    }

    override fun showError(errorMsg: String, errorCode: Int) {

    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }
}