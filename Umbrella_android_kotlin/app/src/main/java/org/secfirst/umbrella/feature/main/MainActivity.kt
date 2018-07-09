package org.secfirst.umbrella.feature.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.feature.base.view.BaseActivity
import org.secfirst.umbrella.feature.content.view.ContentFragment
import org.secfirst.umbrella.feature.form.view.FormFragment
import org.secfirst.umbrella.util.addOrReplaceFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.addOrReplaceFragment(R.id.main_container,
                        ContentFragment.newInstance(), ContentFragment::class.java.name)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                supportFragmentManager.addOrReplaceFragment(R.id.main_container,
                        FormFragment.newInstance(), FormFragment::class.java.name)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }
}

