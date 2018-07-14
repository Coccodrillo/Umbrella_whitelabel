package org.secfirst.umbrella.feature

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import dagger.android.AndroidInjection
import org.secfirst.umbrella.R
import org.secfirst.umbrella.feature.account.AccountController
import org.secfirst.umbrella.feature.base.view.BaseView
import org.secfirst.umbrella.feature.content.view.ContentController
import org.secfirst.umbrella.feature.feed.FeedController
import org.secfirst.umbrella.feature.form.view.FormController
import org.secfirst.umbrella.feature.lesson.LessonController


class MainActivity : AppCompatActivity(), BaseView {

    private lateinit var container: ViewGroup
    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)
        performDI()
        initRoute(savedInstanceState)
    }

    private fun initRoute(savedInstanceState: Bundle?) {
        container = findViewById(R.id.baseContainer)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(FeedController()))
        }
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_feeds -> {
                router.pushController(RouterTransaction.with(FeedController()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_forms -> {
                router.pushController(RouterTransaction.with(FormController()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_checklists -> {
                router.pushController(RouterTransaction.with(ContentController()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_lessons -> {
                router.pushController(RouterTransaction.with(LessonController()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                router.pushController(RouterTransaction.with(AccountController()))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun hideProgress() {}
    override fun showProgress() {}
    private fun performDI() = AndroidInjection.inject(this)
}
