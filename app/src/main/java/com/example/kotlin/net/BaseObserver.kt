package com.example.kotlin.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

import javax.net.ssl.SSLHandshakeException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author : seacool
 * date : 2019/11/20 0020 16:38
 * description :
 */
class BaseObserver<T>(private val responseCallBack: ResponseCallBack<T>, private val loadListener: LoadListener?) :
    Observer<T> {
    private var disposable: Disposable? = null

    override fun onSubscribe(d: Disposable) {
        this.disposable = d
        loadListener?.startLoad()
    }


    override fun onNext(t: T) {
        responseCallBack.onSuccess(t)
    }

    override fun onError(e: Throwable) {
        try {

            if (e is SocketTimeoutException) {
                //请求超时
                responseCallBack.onFail("请求超时,请稍后再试")
            } else if (e is ConnectException) {
                //网络连接超时
                responseCallBack.onFail("网络连接超时,请检查网络状态")
            } else if (e is SSLHandshakeException) {
                //安全证书异常
                responseCallBack.onFail("安全证书异常")
            } else if (e is HttpException) {
                //请求的地址不存在
                val code = e.code()
                if (code == 504) {
                    responseCallBack.onFail("网络异常，请检查您的网络状态")
                } else if (code == 404) {
                    responseCallBack.onFail("请求的地址不存在")
                } else {
                    responseCallBack.onFail("请求失败")
                }
            } else if (e is UnknownHostException) {
                //域名解析失败
                responseCallBack.onFail("域名解析失败")
            } else {
                responseCallBack.onFail("error:" + e.message)
            }
        } catch (e2: Exception) {
            e2.printStackTrace()
        } finally {
            if (disposable != null && !disposable!!.isDisposed) {
                disposable!!.dispose()
            }
            loadListener?.cancelLoad()
        }
    }


    override fun onComplete() {
        //事件完成取消订阅
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
        loadListener?.cancelLoad()
    }
}
