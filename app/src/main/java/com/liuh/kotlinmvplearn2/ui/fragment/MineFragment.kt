package com.liuh.kotlinmvplearn2.ui.fragment

import android.os.Bundle
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment

/**
 * Date: 2018/10/10 09:14
 * Description:
 */
class MineFragment : BaseFragment() {

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {

    }

    override fun lazyLoad() {

    }
}