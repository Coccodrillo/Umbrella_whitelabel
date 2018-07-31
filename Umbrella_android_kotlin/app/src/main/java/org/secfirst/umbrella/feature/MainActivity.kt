package org.secfirst.umbrella.feature

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_view.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.feature.account.AccountController
import org.secfirst.umbrella.feature.content.view.ContentController
import org.secfirst.umbrella.feature.feed.FeedController
import org.secfirst.umbrella.feature.form.view.controller.HostFormController
import org.secfirst.umbrella.feature.lesson.LessonController
import org.secfirst.umbrella.feature.main.OnNavigationBottomView
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class MainActivity : AppCompatActivity(), OnNavigationBottomView {

    private lateinit var container: ViewGroup
    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)
        performDI()
        initRoute(savedInstanceState)
        setUpToolbar()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun setUpToolbar() {
        setSupportActionBar(mainToolbar)
    }

    fun setToolBarTitle(title: String) {
        mainToolbar?.title = title
    }

    fun enableUpArrow(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
        supportActionBar?.setDisplayShowHomeEnabled(enabled)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRoute(savedInstanceState: Bundle?) {
        container = findViewById(R.id.baseContainer)
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
                router.pushController(RouterTransaction.with(HostFormController()))
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

    private fun performDI() = AndroidInjection.inject(this)

    fun getRouter() = router

    override fun showBottomMenu() {
        navigation.visibility = VISIBLE
    }

    override fun hiddenBottomMenu() {
        navigation.visibility = INVISIBLE
    }
}
