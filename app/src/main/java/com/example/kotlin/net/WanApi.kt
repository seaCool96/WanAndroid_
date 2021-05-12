package com.example.kotlin.net

import com.example.kotlin.bean.BaseResult
import com.example.kotlin.bean.HomeArticle
import kotlinx.coroutines.Deferred
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
//    @GET("/article/list/{page}/json")
//    fun getArticleList(@Path("page") page: Int): Observable<BaseResult<List<HomeArticle>>>
    //retrofit 2.6.2版本之前
//    @GET("/article/list/{page}/json")
//     fun getArticleList(@Path("page") page: Int=0): Deferred<BaseResult<List<HomeArticle>>>

    //之后
    @GET("/article/list/{page}/json")
     suspend fun getArticleList(@Path("page") page: Int=0): BaseResult<List<HomeArticle>>

}
