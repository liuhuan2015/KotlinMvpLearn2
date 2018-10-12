package com.liuh.kotlinmvplearn2

/**
 * Date: 2018/10/12 15:52
 * Description:
 */
class Constants private constructor() {

    companion object {
        val BUNDLE_VIDEO_DATA = "video_data"
        val BUNDLE_CATEGORY_DATA = "category_data"

        // 腾讯 Bugly APP id
        val BUGLY_APPID = "176aad0d9e"


        // sp 存储的文件名
        val FILE_WATCH_HISTORY_NAME = "watch_history_file"   //观看记录

        val FILE_COLLECTION_NAME = "collection_file"    //收藏视屏缓存的文件名
    }

}