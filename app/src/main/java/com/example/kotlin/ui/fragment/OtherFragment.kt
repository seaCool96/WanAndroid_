package com.example.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin.R

/**
 *  @author : seacool
 *  date : 2019/11/15 0015 17:05
 *  description :
 */
class OtherFragment : Fragment() {


    private lateinit var v: TextView;
    companion object {
        private const val TAG="111";
        fun getInstance(): OtherFragment {
            return OtherFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view.findViewById(R.id.v);
        v.text= TAG;
    }
}