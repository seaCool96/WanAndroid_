package com.example.kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.bean.BaseResult
import com.example.kotlin.bean.HomeArticle
import com.example.kotlin.net.RetrofitUtil
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {

    val mData = MutableLiveData<List<HomeArticle>>()

    fun getHomeList(page:Int){
        viewModelScope.launch {
           val data = RetrofitUtil.instance.apiService.getArticleList(page)
            mData.value=data?.data.datas
        }

    }
}