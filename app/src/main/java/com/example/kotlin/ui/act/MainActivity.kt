package com.example.kotlin.ui.act

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.kotlin.R
import com.example.kotlin.adapter.MyPageAdapter
import com.example.kotlin.base.BaseVmActivity
import com.example.kotlin.ui.fragment.ClassflyFragment
import com.example.kotlin.ui.fragment.HomeFragment
import com.example.kotlin.ui.fragment.OtherFragment
import com.example.kotlin.viewmodel.MainViewModel

class MainActivity : BaseVmActivity<MainViewModel>() {

    private lateinit var viewPager: ViewPager
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        viewPager = findViewById(R.id.viewPager)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        var fragments = mutableListOf<Fragment>()
        fragments.add(HomeFragment.getInstance())
        fragments.add(ClassflyFragment.getInstance())
        fragments.add(OtherFragment.getInstance())

        val myPageAdapter = MyPageAdapter(fragments, supportFragmentManager)
        viewPager.adapter = myPageAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {
                viewPager.currentItem=position
                navView.menu.getItem(position).isChecked = true
            }
        })
    }

    override fun createObserver() {

    }

    override fun onNetworkStateChanged(netState: Boolean) {
        super.onNetworkStateChanged(netState)
        if (netState) {
            Toast.makeText(applicationContext, "我特么终于有网了啊!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "我特么怎么断网了!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }
}
