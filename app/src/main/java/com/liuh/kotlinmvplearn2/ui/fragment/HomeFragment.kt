package com.liuh.kotlinmvplearn2.ui.fragment

import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment
import com.liuh.kotlinmvplearn2.mvp.contract.HomeContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.HomeBean
import com.liuh.kotlinmvplearn2.mvp.presenter.HomePresenter

class HomeFragment : BaseFragment(), HomeContract.View {

    private val mPresenter by lazy { HomePresenter() }


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun lazyLoad() {

    }

    override fun setHomeData(homeBean: HomeBean) {

    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {

    }

    override fun showError(msg: String, errorCode: Int) {

    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }


}