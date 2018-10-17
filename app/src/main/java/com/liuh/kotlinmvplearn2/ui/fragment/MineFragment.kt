package com.liuh.kotlinmvplearn2.ui.fragment

import android.os.Bundle
import android.view.View
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment

/**
 * Date: 2018/10/10 09:14
 * Description: 我的
 */
class MineFragment : BaseFragment(), View.OnClickListener {

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

    override fun onClick(v: View?) {

    }
}