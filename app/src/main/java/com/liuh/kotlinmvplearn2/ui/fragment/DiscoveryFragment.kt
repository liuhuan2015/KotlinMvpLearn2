package com.liuh.kotlinmvplearn2.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment
import com.liuh.kotlinmvplearn2.base.BaseFragmentAdapter
import com.liuh.kotlinmvplearn2.utils.StatusBarUtil
import com.liuh.kotlinmvplearn2.view.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Date: 2018/10/10 09:06
 * Description: 发现（和热门主界面同样的布局）
 */
class DiscoveryFragment : BaseFragment() {

    private var mTitle: String? = null

    private val tabList = ArrayList<String>()

    private val fragments = ArrayList<Fragment>()

    companion object {
        fun getInstance(title: String): DiscoveryFragment {
            val fragment = DiscoveryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_hot
    }

    override fun initView() {

        // 状态栏透明和间距处理
        StatusBarUtil.darkMode(activity as Activity)
        StatusBarUtil.setPaddingSmart(activity as Activity, toolbar)

        tv_header_title.text = mTitle

        tabList.add("关注")
        tabList.add("分类")

        fragments.add(FollowFragment.getInstance("关注"))
        fragments.add(CategoryFragment.getInstance("分类"))

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragments, tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
    }

    override fun lazyLoad() {

    }
}