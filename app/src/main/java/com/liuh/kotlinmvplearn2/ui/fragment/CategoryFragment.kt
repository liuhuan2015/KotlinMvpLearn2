package com.liuh.kotlinmvplearn2.ui.fragment

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.liuh.kotlinmvplearn2.DisplayManager
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseFragment
import com.liuh.kotlinmvplearn2.mvp.contract.CategoryContract
import com.liuh.kotlinmvplearn2.mvp.model.bean.CategoryBean
import com.liuh.kotlinmvplearn2.mvp.presenter.CategoryPresenter
import com.liuh.kotlinmvplearn2.net.exception.ErrorStatus
import com.liuh.kotlinmvplearn2.showToast
import com.liuh.kotlinmvplearn2.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Date: 2018/10/15 14:24
 * Description: 发现--->分类
 */
class CategoryFragment : BaseFragment(), CategoryContract.View {

    private var mTitle: String? = null

    private var mCategoryList = ArrayList<CategoryBean>()

    private val mPresenter by lazy { CategoryPresenter() }

    private val mAdapter by lazy { CategoryAdapter(activity as Context, mCategoryList, R.layout.item_category) }

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
        mPresenter.attachView(this)

        mLayoutStatusView = multipleStatusView

        mRecyclerView.adapter = mAdapter

        mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val position = parent.getChildPosition(view)
                val offset = DisplayManager.dip2px(2f)!!

                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                        if (position % 2 == 0) offset else 0, offset)
            }
        })

    }

    override fun lazyLoad() {
        mPresenter.getCategoryData()
    }

    /**
     * 显示分类信息
     */
    override fun showCategory(categoryList: ArrayList<CategoryBean>) {
        mCategoryList = categoryList
        mAdapter.setData(mCategoryList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView?.showNoNetwork()
        } else {
            multipleStatusView?.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView?.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView?.showContent()
    }
}