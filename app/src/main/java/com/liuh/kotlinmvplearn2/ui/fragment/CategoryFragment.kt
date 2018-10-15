package com.liuh.kotlinmvplearn2.ui.fragment

import android.os.Bundle
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment
import com.liuh.kotlinmvplearn2.mvp.contract.CategoryContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.CategoryBean
import com.liuh.kotlinmvplearn2.mvp.presenter.CategoryPresenter
import com.liuh.kotlinmvplearn2.ui.adapter.CategoryAdapter

/**
 * Date: 2018/10/15 14:24
 * Description: 发现--->分类
 */
class CategoryFragment : BaseFragment(), CategoryContract.View {

    private var mTitle: String? = null

    private var mCategoryList = ArrayList<CategoryBean>()

    private val mPresenter by lazy { CategoryPresenter() }

    private val mAdpter by lazy { CategoryAdapter(activity, mCategoryList, R.layout.item_category) }

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

    override fun showCategory(categoryList: ArrayList<CategoryBean>) {

    }

    override fun showError(errorMsg: String, errorCode: Int) {

    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }
}