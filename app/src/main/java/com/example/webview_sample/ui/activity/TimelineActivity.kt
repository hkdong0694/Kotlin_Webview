package com.example.webview_sample.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.webview_sample.R
import com.example.webview_sample.adapter.PagerAdapter
import com.example.webview_sample.ui.fragment.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab_layout.view.*

/**
 * Webview_Sample
 * Class: TimelineActivity
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class TimelineActivity : AppCompatActivity() {

    interface OnBackPressedListener {
        fun onBackPressed()
    }

    lateinit var onBackListener:OnBackPressedListener

    fun setListener(listener: OnBackPressedListener?) {
        if (listener != null) {
            onBackListener = listener
        }
    }

    override fun onBackPressed() {
        if(onBackListener != null) {
            onBackListener.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
    }

    private fun createView(name: String): View {
        val view = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null)
        view.tv_title.text = name
        return view
    }

    private fun initViewPager() {
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addItems(TimelineMainFragment(getString(R.string.fragment_title_today)))
        adapter.addItems(TimelineSPayFragment(getString(R.string.fragment_title_SPay)))
        adapter.addItems(TimelineAssetFragment(getString(R.string.fragment_title_assets)))
        adapter.addItems(TimelineDiscoverFragment(getString(R.string.fragment_title_discover)))
        adapter.addItems(TimelineInfoFragment(getString(R.string.fragment_title_info)))
        vp_main.adapter = adapter
        vp_main.offscreenPageLimit = adapter.count
        tl_main.setupWithViewPager(vp_main)

        tl_main.getTabAt(0)?.customView = createView(getString(R.string.fragment_title_today))
        tl_main.getTabAt(1)?.customView = createView(getString(R.string.fragment_title_SPay))
        tl_main.getTabAt(2)?.customView = createView(getString(R.string.fragment_title_assets))
        tl_main.getTabAt(3)?.customView = createView(getString(R.string.fragment_title_discover))
        tl_main.getTabAt(4)?.customView = createView(getString(R.string.fragment_title_info))

        tl_main.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("MainActivity", "${tab?.position!!} 접근! " )
                vp_main.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}