package com.example.kotlin.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.recevier.NetworkStateManager
import com.example.kotlin.recevier.NetworkStateReceive
import java.lang.reflect.ParameterizedType

/**
 * 描述　: ViewModelActivity基类，把ViewModel注入进来了
 */
abstract class BaseVmActivity<VM : ViewModel> : AppCompatActivity() {

    lateinit var mViewModel: VM
    private var mNetworkStateReceive: NetworkStateReceive? = null
    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        //观察网络状态
        mNetworkStateReceive = NetworkStateReceive()
        registerReceiver(
            mNetworkStateReceive,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(getVmClazz(this))
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer {
            onNetworkStateChanged(it)
        })
    }

    fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: Boolean) {

    }


    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    override fun onDestroy() {
        super.onDestroy()
        if (mNetworkStateReceive!=null){
            unregisterReceiver(mNetworkStateReceive)
        }
    }
}