package com.example.kotlin.bean

/**
 * @author : seacool
 * date : 2019/11/20 0020 16:33
 * description :
 */
data class BaseResult<T>(val errorCode: Int, val errorMsg: String?, val data: DataBean<T>)

