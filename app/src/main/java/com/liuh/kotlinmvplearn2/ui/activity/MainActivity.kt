package com.liuh.kotlinmvplearn2.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseActivity
import com.liuh.kotlinmvplearn2.mvp.model.bean.TabEntity
import com.liuh.kotlinmvplearn2.showToast
import com.liuh.kotlinmvplearn2.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)

    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    //底部tab
    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mDiscoveryFragment: DiscoveryFragment? = null
    private var mHotFragment: HotFragment? = null
    private var mMineFragment: MineFragment? = null

    // 默认为0
    private var mIndex = 0

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }

        super.onCreate(savedInstanceState)

        initTab()

        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }


    // 初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }

        tab_layout.setTabData(mTabEntities)

        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {

            override fun onTabSelect(position: Int) {
                // 切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    // 切换 Fragment
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 -> {
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance(mTitles[position])
                    transaction.add(R.id.fl_container, mHomeFragment)
                } else {
                    transaction.show(mHomeFragment)
                }
            }

            1 -> {
                if (mDiscoveryFragment == null) {
                    mDiscoveryFragment = DiscoveryFragment.getInstance(mTitles[position])
                    transaction.add(R.id.fl_container, mDiscoveryFragment)
                } else {
                    transaction.show(mDiscoveryFragment)
                }
            }

            2 -> {
                if (mHotFragment == null) {
                    mHotFragment = HotFragment.getInstance(mTitles[position])
                    transaction.add(R.id.fl_container, mHotFragment)
                } else {
                    transaction.show(mHotFragment)
                }
            }

            3 -> {
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.getInstance(mTitles[position])
                    transaction.add(R.id.fl_container, mMineFragment)
                } else {
                    transaction.show(mMineFragment)
                }
            }
            else -> {
            }
        }
        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment)
        }

        if (null != mDiscoveryFragment) {
            transaction.hide(mDiscoveryFragment)
        }

        if (null != mHotFragment) {
            transaction.hide(mHotFragment)
        }
        if (null != mMineFragment) {
            transaction.hide(mMineFragment)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 记录fragment的位置，防止崩溃时 activity被系统回收，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun start() {

    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

}
