package com.example.webview_sample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webview_sample.R
import kotlinx.android.synthetic.main.custom_tab_layout.*

/**
 * Webview_Sample
 * Class: TimelineMainFragment
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class TimelineMainFragment(var title: String): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.timeline_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_title.text = title
        super.onViewCreated(view, savedInstanceState)
    }
}