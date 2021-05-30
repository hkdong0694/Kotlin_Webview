package com.example.webview_sample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.webview_sample.R
import com.example.webview_sample.ui.activity.TimelineActivity
import kotlinx.android.synthetic.main.timeline_fragment.*

/**
 * Webview_Sample
 * Class: TimelineInfoFragment
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class TimelineInfoFragment(var title: String): Fragment(), TimelineActivity.OnBackPressedListener {
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
        wv_main.webChromeClient = WebChromeClient()
        var settings = wv_main.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.domStorageEnabled = true
        wv_main.loadUrl("https://ddangeun.tistory.com/73")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        if(wv_main.canGoBack()) {
            wv_main.goBack()
        } else {
            requireActivity().onBackPressed()
        }
    }
}