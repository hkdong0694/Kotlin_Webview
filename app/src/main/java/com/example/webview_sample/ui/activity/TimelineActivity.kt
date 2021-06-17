package com.example.webview_sample.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.webview_sample.R
import com.example.webview_sample.adapter.PagerAdapter
import com.example.webview_sample.ui.fragment.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_common_toolbar.*
import kotlinx.android.synthetic.main.custom_tab_layout.view.*


/**
 * Webview_Sample
 * Class: TimelineActivity
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class TimelineActivity : AppCompatActivity() {

    var fragmentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentIndex = 0
        initViewPager()
    }

    private fun createView(name: String): View {
        val view = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null)
        view.tv_title.text = name
        return view
    }

    override fun onNewIntent(intent: Intent?) {
        if( intent != null ) {
            val title = intent.getStringExtra("title")
            val menu = intent.getBooleanExtra("menu", false)
            val close = intent.getBooleanExtra("close", false)
            val search = intent.getBooleanExtra("search", false)
            val searchurl = intent.getStringExtra("searchurl")
            Log.d("TimelineActivity", "$title : $close : $menu :  $search : $searchurl")
        }
        super.onNewIntent(intent)
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
                fragmentIndex = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        // appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
        //     val percentage: Float = abs(verticalOffset) / appBarLayout.totalScrollRange.toFloat()
        //     when {
        //         percentage < 0.1 ->  Log.d("ok", "ㅇㅇ??")
        //         percentage >= 1.0.toFloat() -> {
        //             // AppbarLayout 영역이 완전히 사라졌을 경우!
        //             custom1.visibility = View.VISIBLE
        //             tl_main.visibility = View.GONE
        //         }
        //         else ->  {
        //             custom1.visibility = View.GONE
        //             tl_main.visibility = View.VISIBLE
        //         }
        //     }
        // })
    }

    override fun onBackPressed() {
        when(fragmentIndex) {
            0 -> {
                super.onBackPressed()
            }
            1 -> {
                fragmentIndex = 0
                vp_main.currentItem = fragmentIndex
            }
            2 -> {
                fragmentIndex = 1
                vp_main.currentItem = fragmentIndex
            }
            3 -> {
                fragmentIndex = 2
                vp_main.currentItem = fragmentIndex
            }
            4 -> {
                fragmentIndex = 3
                vp_main.currentItem = fragmentIndex
            }
        }
    }
}