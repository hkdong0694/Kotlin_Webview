package com.example.webview_sample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.webview_sample.ui.fragment.TimelineMainFragment
import java.util.ArrayList

/**
 * Webview_Sample
 * Class: PagerAdapter
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class PagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    // BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 써줘야함!!

    private var fragmentList: MutableList<TimelineMainFragment> = ArrayList()

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]

    fun addItems(fragment: TimelineMainFragment) = fragmentList.add(fragment)

}