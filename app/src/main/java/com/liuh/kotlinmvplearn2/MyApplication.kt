package com.liuh.kotlinmvplearn2

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

class MyApplication : Application() {

    private var refWatcher: RefWatcher? = null

    companion object {

        private val TAG = "MyApplication"

        var context: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        initConfig()

        DisplayManager.init(this)

        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    /**
     * 初始化配置
     */
    private fun initConfig() {

        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false) // 隐藏线程信息 默认：显示
                .methodCount(0)
                .methodOffset(7)
                .tag("liu_h")
                .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.i(TAG, "onActivityCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.i(TAG, "onActivityStarted: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.i(TAG, "onActivityDestroyed: " + activity.componentName.className)
        }

    }


}





















