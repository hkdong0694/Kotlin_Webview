package com.example.webview_sample.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.webview_sample.R

/**
 * Webview_Sample
 * Class: CustomToolbar
 * Created by 한경동 (Joel) on 2021/05/30.
 * Description:
 */
class CustomToolbar : FrameLayout, View.OnClickListener {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }


    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.custom_common_toolbar, this, true)

    }


    override fun onClick(v: View?) {

    }

}