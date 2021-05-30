# Kotlin_Webview
CoordinatorLayout + NestedScrollView + ViewPager + Webview
스크롤 제어 및 뒤로 가기 제어 Sample 예제


## TimelineActivity.kt

~~~kotlin


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

        // AppbarLayout 에 정의 되어 있는 xml 스크롤 제어 Event Listener
        appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val percentage: Float = abs(verticalOffset) / appBarLayout.totalScrollRange.toFloat()
            when {
                percentage < 0.1 ->  Log.d("ok", "ㅇㅇ??")
                percentage >= 1.0.toFloat() -> {
                    // AppbarLayout 영역이 완전히 사라졌을 경우!
                    custom1.visibility = View.VISIBLE
                    tl_main.visibility = View.GONE
                }
                else ->  {
                    custom1.visibility = View.GONE
                    tl_main.visibility = View.VISIBLE
                }
            }
        })
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

~~~

## activity_main.xml

~~~kotlin

<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.activity.TimelineActivity">

    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <com.google.android.material.appbar.AppBarLayout
            android:id="@+id/appbarLayout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

            <com.google.android.material.appbar.CollapsingToolbarLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:contentScrim="@android:color/transparent"
                app:layout_scrollFlags="scroll|enterAlwaysCollapsed">

            <com.example.webview_sample.component.CustomToolbar
                android:id="@+id/custom"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:popupTheme="@style/Theme.AppCompat.Light"
                app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />

            </com.google.android.material.appbar.CollapsingToolbarLayout>

                <com.google.android.material.tabs.TabLayout
                    android:id="@+id/tl_main"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:elevation="0dp"
                    app:tabGravity="fill"
                    app:tabIndicatorColor="@color/cardview_dark_background"
                    app:tabMode="fixed" />

            <com.example.webview_sample.component.CustomToolbar
                android:id="@+id/custom1"
                android:visibility="gone"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:popupTheme="@style/Theme.AppCompat.Light"
                app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />

        </com.google.android.material.appbar.AppBarLayout>

        <androidx.viewpager.widget.ViewPager
            android:id="@+id/vp_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">

        </androidx.viewpager.widget.ViewPager>

    </androidx.coordinatorlayout.widget.CoordinatorLayout>

</androidx.constraintlayout.widget.ConstraintLayout>

~~~

## TimelineMainFragment.kt

~~~kotlin

class TimelineMainFragment(var title: String): Fragment() {

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
        wv_main.loadUrl("https://daum.net")
        wv_main.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && wv_main.canGoBack()) {
                    wv_main.goBack()
                    return true
                }
                return false
            }
        })

    }
}

~~~

## timeline_fragment.xml

~~~kotlin

<androidx.core.widget.NestedScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <WebView
            android:id="@+id/wv_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </LinearLayout>


</androidx.core.widget.NestedScrollView>

~~~
