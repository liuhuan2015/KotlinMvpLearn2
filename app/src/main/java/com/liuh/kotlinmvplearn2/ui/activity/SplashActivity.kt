package com.liuh.kotlinmvplearn2.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.liuh.kotlinmvplearn2.MyApplication
import com.liuh.kotlinmvplearn2.R
import com.liuh.kotlinmvplearn2.base.BaseActivity
import com.liuh.kotlinmvplearn2.showToast
import com.liuh.kotlinmvplearn2.utils.AppUtils
import kotlinx.android.synthetic.main.activity_splash.*
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem

/**
 * 启动页
 */
class SplashActivity : BaseActivity() {

    private var textTypeface: Typeface? = null

    private var descTypeface: Typeface? = null

    private var alphaAnimation: AlphaAnimation? = null

    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")
        descTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }


    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {

    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        tv_app_name.typeface = textTypeface
        tv_splash_desc.typeface = descTypeface
        tv_version_name.text = "v${AppUtils.getVerName(MyApplication.context)}"

        /**
         * 渐变展示启动屏
         */
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                redirectTo()
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })

        checkPermission()

    }

    override fun start() {

    }

    private fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * android 动态权限申请
     */
    private fun checkPermission() {
        val permissionItems = ArrayList<PermissionItem>()
        permissionItems.add(PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone))
        permissionItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储空间", R.drawable.permission_ic_storage))

        HiPermission.create(this)
                .title("你好")
                .msg("为了应用能够正常使用，请开启以下权限！")
                .permissions(permissionItems)
                .style(R.style.PermissionDefaultBlueStyle)
                .animStyle(R.style.PermissionAnimScale)
                .checkMutiPermission(object : PermissionCallback {
                    override fun onFinish() {
                        showToast("初始化完毕")
                        layout_splash.startAnimation(alphaAnimation)
                    }

                    override fun onDeny(permission: String?, position: Int) {
                        Log.e("---", "permission onDeny")
                    }

                    override fun onGuarantee(permission: String?, position: Int) {
                        Log.e("---", "permission onGuarantee")
                    }

                    override fun onClose() {
                        showToast("用户关闭了权限")
                    }
                })


    }
}