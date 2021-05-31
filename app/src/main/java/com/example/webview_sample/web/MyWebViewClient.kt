package com.example.webview_sample.web

import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Webview_Sample
 * Class: MyWebViewClient
 * Created by 한경동 (Joel) on 2021/05/30.
 * Description:
 */
class MyWebViewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
      return false
    }
}