package com.liuh.kotlinmvplearn2.ui.activity

import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseActivity
import com.liuh.kotlinmvplearn2.mvp.contract.CategoryDetailContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean

/**
 * Date: 2018/10/16 09:31
 * Description: 分类的详情
 */
class CategoryDetailActivity : BaseActivity(), CategoryDetailContract.View {

    override fun layoutId(): Int = R.layout.activity_category_detail

    override fun initData() {

    }

    override fun initView() {

    }

    override fun start() {

    }

    override fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>) {

    }

    override fun showError(errorMsg: String) {

    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }
}