package com.example.kotlin.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.ViewSkeletonScreen
import com.example.kotlin.R
import com.example.kotlin.adapter.HomeAdapter
import com.example.kotlin.base.BaseVmFragment
import com.example.kotlin.bean.BaseResult
import com.example.kotlin.bean.HomeArticle
import com.example.kotlin.net.BaseObserver
import com.example.kotlin.net.LoadListener
import com.example.kotlin.net.ResponseCallBack
import com.example.kotlin.net.RetrofitUtil
import com.example.kotlin.viewmodel.HomeViewModel
import com.example.kotlin.viewmodel.LoginViewModel
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*


/**
 *  @author : seacool
 *  date : 2019/11/15 0015 17:05
 *  description :
 */
class HomeFragment : BaseVmFragment<HomeViewModel>() {

    private var pageNo: Int = 0
    private var mHomeAdapter: HomeAdapter? = null
    private var skeletonScreen: RecyclerViewSkeletonScreen? = null
    private var loadMore: Boolean? = false

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        mHomeAdapter = HomeAdapter(R.layout.item_home)
        rvlist?.layoutManager = LinearLayoutManager(activity)
        rvlist?.adapter = mHomeAdapter
        //设置 Header 为 贝塞尔雷达 样式
        srl?.setRefreshHeader(MaterialHeader(activity))
        //设置 Footer 为 球脉冲 样式
        srl?.setRefreshFooter(ClassicsFooter(activity));
        //骨架屏
        skeletonScreen = Skeleton.bind(rvlist)
            .adapter(mHomeAdapter)
            .load(R.layout.item_home_skeleton)
            .duration(2000)
            .show()

        Handler().postDelayed({ lazyLoadData() }, 2000)

        srl?.setOnRefreshListener {
            pageNo = 0
            loadMore = false
            lazyLoadData()
        }
        srl?.setOnLoadMoreListener { refreshlayout ->
            refreshlayout.finishLoadMore(2000/*,false*/)//传入false表示加载失败
            pageNo++
            loadMore = true
            lazyLoadData()
        }

    }

    override fun lazyLoadData() {
        mViewModel.getHomeList(pageNo)
    }

    override fun createObserver() {
        mViewModel.mData.observe(this, Observer {
            if (loadMore!!) {
                it.let { mHomeAdapter?.addData(it) }
                srl.finishLoadMore()
            } else {
                mHomeAdapter?.setNewData(it)
                srl.finishRefresh()
            }
            skeletonScreen?.hide()
        })
    }

    override fun showLoading(message: String) {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }
//    private fun initData(loadMore: Boolean) {
//
//        RetrofitUtil.instance.apiService.getArticleList(pageNo)
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(BaseObserver(object : ResponseCallBack<BaseResult<List<HomeArticle>>> {
//                override fun onSuccess(homeArticleBaseResult: BaseResult<List<HomeArticle>>) {
//                   if (loadMore){
//                       homeArticleBaseResult.data?.datas?.let { mHomeAdapter?.addData(it) }
//                       srl.finishLoadMore()
//                   }else{
//                       mHomeAdapter?.setNewData(homeArticleBaseResult.data?.datas)
//                       srl.finishRefresh()
//                   }
//                    skeletonScreen?.hide()
//                }
//
//                override fun onFail(errorMsg: String) {
//
//                }
//            }, object : LoadListener {
//                override fun startLoad() {
//
//                }
//
//                override fun cancelLoad() {
//
//                }
//            }))
//    }
}