package com.liuh.kotlinmvplearn2.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment
import com.liuh.kotlinmvplearn2.base.BaseFragmentAdapter
import com.liuh.kotlinmvplearn2.mvp.contract.HotTabContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.TabInfoBean
import com.liuh.kotlinmvplearn2.mvp.presenter.HotTabPresenter
import com.liuh.kotlinmvplearn2.net.exception.ErrorStatus
import com.liuh.kotlinmvplearn2.showToast
import com.liuh.kotlinmvplearn2.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Date: 2018/10/10 09:12
 * Description:
 */
class HotFragment : BaseFragment(), HotTabContract.View {

    private var mTitle: String? = null

    private val mPresenter by lazy { HotTabPresenter() }


    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()


    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_hot


    override fun initView() {
        mLayoutStatusView = multipleStatusView
        // 状态栏透明和间距处理
        StatusBarUtil.darkMode(activity as Activity)
        StatusBarUtil.setPaddingSmart(activity as Activity, toolbar)
    }

    override fun lazyLoad() {
        mPresenter.getTabInfo()
    }

    override fun setTabInfo(tabInfoBean: TabInfoBean) {

        tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList) { it.name }
        tabInfoBean.tabInfo.tabList.mapTo(mFragmentList) { RankFragment.getInstance(it.apiUrl) }

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, mFragmentList, mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
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

    }

    override fun dismissLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}