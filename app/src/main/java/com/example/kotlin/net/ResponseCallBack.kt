package com.example.kotlin.net

/**
 * @author : seacool
 * date : 2019/11/20 0020 16:40
 * description :
 */
interface ResponseCallBack<T> {
    fun onSuccess(t: T)

    fun onFail(errorMsg: String)
}
