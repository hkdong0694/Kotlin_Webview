package com.example.webview_sample.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import com.example.webview_sample.R
import com.example.webview_sample.ui.activity.TimelineActivity
import com.example.webview_sample.web.MyWebViewClient
import kotlinx.android.synthetic.main.timeline_fragment.*

/**
 * Webview_Sample
 * Class: TimelineDiscoverFragment
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class TimelineDiscoverFragment(var title: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.timeline_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wv_main.webViewClient = MyWebViewClient()
        wv_main.webChromeClient = WebChromeClient()
        var settings = wv_main.settings
        settings.javaScriptEnabled = true
        wv_main.loadUrl("https://github.com/hkdong0694")
        wv_main.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && wv_main.canGoBack()) {
                wv_main.goBack()
                true
            } else {
                false
            }
        }

    }
}