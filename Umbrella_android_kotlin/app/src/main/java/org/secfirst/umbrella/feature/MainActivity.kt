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
import org.secfirst.umbrella.feature.base.view.BaseView
import org.secfirst.umbrella.feature.content.view.ContentFragment
import org.secfirst.umbrella.feature.form.view.FormController
import org.secfirst.umbrella.feature.main.SampleController

class MainActivity : AppCompatActivity(), BaseView {

    private lateinit var container: ViewGroup
    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        performDI()
        initRoute(savedInstanceState)
    }

    private fun initRoute(savedInstanceState: Bundle?) {
        container = findViewById(R.id.baseContainer)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(SampleController()))
        }
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                router.pushController(RouterTransaction.with(SampleController()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //openFragment(ContentFragment.newInstance())
                router.pushController(RouterTransaction.with(ContentFragment()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                router.pushController(RouterTransaction.with(FormController()))
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
