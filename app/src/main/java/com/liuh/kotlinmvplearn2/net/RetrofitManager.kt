package com.liuh.kotlinmvplearn2.net

import com.liuh.kotlinmvplearn2.MyApplication
import com.liuh.kotlinmvplearn2.api.ApiService
import com.liuh.kotlinmvplearn2.api.UriConstant
import com.liuh.kotlinmvplearn2.utils.Preference
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Date: 2018/9/25 08:50
 * Description:
 */
object RetrofitManager {

    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null


    val service: ApiService by lazy {
        getRetrofit()!!.create(ApiService::class.java)
    }

    private var token: String by Preference("token", "")

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    // 添加一个log拦截器，打印所有的log
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    // 设置请求过滤的水平，body，basic，headers
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                    // 设置请求的缓存的大小和位置
                    val cacheFile = File(MyApplication.context.cacheDir, "cache")
                    val cache = Cache(cacheFile, 1024 * 1024 * 50)// 50M 缓存大小

                    client = OkHttpClient.Builder()
                            .addInterceptor(addQueryParameterInterceptor()) // 参数过滤
                            .addInterceptor(addHeaderInterceptor()) // token 过滤
                            .addInterceptor(httpLoggingInterceptor) // 日志，所有的请求响应都能看到
                            .cache(cache) // 添加缓存
                            .connectTimeout(60L, TimeUnit.SECONDS)
                            .readTimeout(60L, TimeUnit.SECONDS)
                            .writeTimeout(60L, TimeUnit.SECONDS)
                            .build()

                    retrofit = Retrofit.Builder()
                            .baseUrl(UriConstant.BASE_URL)
                            .client(client!!)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

                }
            }
        }
        return retrofit
    }


    /**
     * 设置公共参数
     */
    private fun addQueryParameterInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request

            val modifiedUrl = originalRequest.url().newBuilder()
                    // 在这里把我们自己的 parameter 添加进去
                    .addQueryParameter("phoneSystem", "")
                    .addQueryParameter("phoneModel", "")
                    .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

    /**
     * 设置头
     */
    private fun addHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .header("token", token)
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }

    }


}