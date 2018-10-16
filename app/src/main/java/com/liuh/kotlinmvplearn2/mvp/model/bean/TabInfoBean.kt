package com.liuh.kotlinmvplearn2.mvp.model.bean

/**
 * Date: 2018/10/16 10:56
 * Description: 热门的 tabInfo
 */
data class TabInfoBean(val tabInfo: TabInfo) {

    data class TabInfo(val tabList: ArrayList<Tab>)

    data class Tab(val id: Long, val name: String, val apiUrl: String)

}