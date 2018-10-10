package com.liuh.kotlinmvplearn2.ui.fragment

import android.os.Bundle
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment

/**
 * Date: 2018/10/10 09:12
 * Description:
 */
class HotFragment : BaseFragment() {

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
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

    }

    override fun lazyLoad() {

    }
}