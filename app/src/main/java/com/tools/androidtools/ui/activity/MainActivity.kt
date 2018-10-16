package com.tools.androidtools.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.tools.androidtools.R
import com.tools.androidtools.ui.adapter.ViewPagerAdapter
import com.tools.androidtools.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fragmentList =
            arrayListOf<Fragment>(OneFragment(), TwoFragment(), ThreeFragment(), FourFragment())

    val mViewPagerAdapter = ViewPagerAdapter(fragmentList, supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initData()
        initListener()
    }

    private fun initListener() {
        mAppBottomBnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.app_navigation_tab_one -> {
                    mAppVp.currentItem = 0
                }
                R.id.app_navigation_contact -> {
                    mAppVp.currentItem = 1
                }
                R.id.app_navigation_find -> {
                    mAppVp.currentItem = 2
                }
                R.id.app_navigation_mine -> {
                    mAppVp.currentItem = 3
                }
            }
            false
        }
        mAppVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                mAppBottomBnv.menu.getItem(position).isChecked = true
            }

        })
    }

    private fun initData() {
        mAppVp.adapter = mViewPagerAdapter
        mAppVp.offscreenPageLimit = 2
    }

    private fun initView() {
        mAppBottomBnv.itemIconTintList = null
    }

}
