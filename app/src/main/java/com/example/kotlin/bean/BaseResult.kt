package com.example.kotlin.bean

/**
 * @author : seacool
 * date : 2019/11/20 0020 16:33
 * description :
 */
class BaseResult<T> {
    var errorCode: Int = 0
    var errorMsg: String? = null
    var data: DataBean<T>? = null
}
