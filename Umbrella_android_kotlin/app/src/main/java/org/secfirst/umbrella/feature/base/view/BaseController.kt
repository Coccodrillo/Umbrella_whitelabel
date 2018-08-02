package org.secfirst.umbrella.feature.base.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bluelinelabs.conductor.Controller
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*
import org.secfirst.umbrella.feature.MainActivity

abstract class BaseController(bundle: Bundle = Bundle()) : Controller(bundle), LayoutContainer {

    init {
        inject()
    }

    private fun inject() = onInject()

    protected abstract fun onInject()

    override val containerView: View?
        get() = view

    override fun onAttach(view: View) {
        super.onAttach(view)
        val mainActivity = activity as MainActivity
        mainActivity.setToolBarTitle(getTitleToolbar())
        mainActivity.enableUpArrow(getEnableBackAction())
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        clearFindViewByIdCache()
    }

    protected fun View.hideKeyboard() {
        val inputMethodManager = applicationContext?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }

    protected abstract fun getEnableBackAction(): Boolean
    protected abstract fun getTitleToolbar(): String
}

