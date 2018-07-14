package org.secfirst.umbrella.feature.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection


abstract class BaseActivity : AppCompatActivity(), BaseView, BaseFragment.CallBack {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    override fun hideProgress() {
        //stuff
    }

    override fun showProgress() {
        //stuff
    }

    private fun performDI() = AndroidInjection.inject(this)

}