package com.liuh.kotlinmvplearn2.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.*
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import java.io.InputStream

/**
 * Date: 2018/10/11 11:30
 * Description:
 */
class CustomBaseGlideUrlLoader(concreteLoader: ModelLoader<GlideUrl, InputStream>, modelCache: ModelCache<String, GlideUrl>) : BaseGlideUrlLoader<String>(concreteLoader, modelCache) {

    override fun getUrl(model: String?, width: Int, height: Int, options: Options?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handles(model: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}