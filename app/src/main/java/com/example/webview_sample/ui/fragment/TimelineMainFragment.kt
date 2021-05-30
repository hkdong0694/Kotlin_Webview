package com.example.webview_sample.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.webview_sample.R
import com.example.webview_sample.ui.activity.TimelineActivity
import kotlinx.android.synthetic.main.custom_tab_layout.*
import kotlinx.android.synthetic.main.timeline_fragment.*

/**
 * Webview_Sample
 * Class: TimelineMainFragment
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class TimelineMainFragment(var title: String): Fragment(), TimelineActivity.OnBackPressedListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.timeline_fragment, container, false)

    override fun onResume() {
        super.onResume()
        (activity as TimelineActivity).setListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        wv_main.webViewClient = WebViewClient()
        var settings = wv_main.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.domStorageEnabled = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        wv_main.loadUrl("https://www.google.com/search?q=ekdma&rlz=1C5CHFA_enKR890KR890&oq=ekdma&aqs=chrome..69i57j0i433j69i60l6.461j0j7&sourceid=chrome&ie=UTF-8")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        Log.d("TimelineAssetFragment", "Main 프래그먼트에서 백버튼 호출!")
    }
}