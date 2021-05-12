package com.example.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.R
import com.example.kotlin.bean.HomeArticle

/**
 * @author : seacool
 * date : 2019/11/21 0021 10:42
 * description :
 */
class HomeAdapter(layoutResId: Int) : BaseQuickAdapter<HomeArticle, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: HomeArticle) {
        helper.setText(R.id.tv_title,item.title)
    }

}
