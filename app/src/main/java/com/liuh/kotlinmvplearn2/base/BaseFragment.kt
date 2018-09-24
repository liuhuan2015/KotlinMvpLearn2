package com.liuh.kotlinmvplearn2.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView
import com.liuh.kotlinmvplearn2.MyApplication

/**
 * 继承 BaseFragemnt 的Fragment都是用懒加载加载数据
 */
abstract class BaseFragment : Fragment() {

    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false

    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true

        initView()

        lazyLoadDataIfPrepared()

        // 多种状态切换的 view，重试点击事件

        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }


    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }

    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { MyApplication.getRefWatcher(it)?.watch(activity) }
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun lazyLoad()

}