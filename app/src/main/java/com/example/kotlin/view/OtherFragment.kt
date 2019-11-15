package com.example.kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin.R

/**
 *  @author : seacool
 *  date : 2019/11/15 0015 17:05
 *  description :
 */
class OtherFragment : Fragment() {

    companion object {
        fun getInstance(): OtherFragment {
            return OtherFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_other, container, false)
    }
}