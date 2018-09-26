package com.liuh.kotlinmvplearn2.mvp.model.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Date: 2018/9/26 11:17
 * Description:
 */
class TabEntity(var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }
}