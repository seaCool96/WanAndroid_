package com.example.kotlin.net

import com.example.kotlin.bean.BaseResult
import com.example.kotlin.bean.HomeArticle
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author : seacool
 * date : 2019/11/15 0015 17:54
 * description :玩android 的api
 */
interface WanApi {

    companion object {

        val BASE_URL = "https://www.wanandroid.com"
    }

    /**
     * 首页文章列表
     *
     * @param page
     * @return
     */
    @GET("/article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<BaseResult<HomeArticle>>

}
