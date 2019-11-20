package com.example.kotlin.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * @author : seacool
 * date : 2019/11/20 0020 15:25
 * description :
 */
class RetrofitUtil private constructor() {

    private val retrofit: Retrofit

    val apiService: WanApi
        get() = retrofit.create(WanApi::class.java)

    init {
        //全局拦截器
        val tokenInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()//获取原始请求
            val requestBuilder = originalRequest.newBuilder() //建立新的请求
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .removeHeader("User-Agent")
                .method(originalRequest.method(), originalRequest.body())
            chain.proceed(requestBuilder.build()) //重新请求
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addNetworkInterceptor(tokenInterceptor)
            .retryOnConnectionFailure(true)
            .build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(WanApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {

        private var retrofitUtil: RetrofitUtil? = null

        val instance: RetrofitUtil
            @Synchronized get() {
                if (retrofitUtil == null) {
                    retrofitUtil = RetrofitUtil()
                }
                return retrofitUtil as RetrofitUtil
            }
    }
}
