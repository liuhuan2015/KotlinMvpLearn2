package com.liuh.kotlinmvplearn2

import android.os.Bundle
import com.flyco.tablayout.listener.CustomTabEntity
import com.liuh.kotlinmvplearn2.base.BaseActivity
import com.liuh.kotlinmvplearn2.ui.fragment.HomeFragment

class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)

    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    //底部tab
    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null

    override fun layoutId(): Int {
        return 0
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun start() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
