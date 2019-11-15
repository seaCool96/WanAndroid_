package com.example.kotlin.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.kotlin.R
import com.example.kotlin.adapter.MyPageAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}
