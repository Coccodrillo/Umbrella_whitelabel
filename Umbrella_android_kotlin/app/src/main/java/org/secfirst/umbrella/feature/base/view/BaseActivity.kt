package org.secfirst.umbrella.feature.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import org.secfirst.umbrella.util.removeFragment

abstract class BaseActivity : AppCompatActivity(), BaseView, BaseFragment.CallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager.removeFragment(tag = tag)
    }

    private fun performDI() = AndroidInjection.inject(this)
}
