package com.liuh.kotlinmvplearn2.ui.fragment

import android.os.Bundle
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment

/**
 * Date: 2018/10/15 14:24
 * Description: 发现--->分类
 */
class CategoryFragment : BaseFragment() {

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): CategoryFragment {
            val fragment = CategoryFragment()
            val bundle = Bundle()

            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {

    }

    override fun lazyLoad() {

    }
}