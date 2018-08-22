package org.secfirst.umbrella.whitelabel.feature.base.view

import android.os.Bundle
import android.view.View
import com.bluelinelabs.conductor.Controller
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*
import org.secfirst.umbrella.whitelabel.feature.main.MainActivity

abstract class BaseController(bundle: Bundle = Bundle()) : Controller(bundle), LayoutContainer {

    private lateinit var mainActivity: MainActivity

    init {
        inject()
    }

    private fun inject() = onInject()

    protected abstract fun onInject()

    override val containerView: View?
        get() = view

    override fun onAttach(view: View) {
        super.onAttach(view)
        mainActivity = activity as MainActivity
        mainActivity.setToolBarTitle(getTitleToolbar())
        mainActivity.enableUpArrow(getEnableBackAction())
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        clearFindViewByIdCache()
    }

    protected abstract fun getEnableBackAction(): Boolean

    protected abstract fun getTitleToolbar(): String

    protected abstract fun getEnableToolbar(): Boolean

    fun enableNavigation() = mainActivity.showNavigation()

    fun disableNavigation() = mainActivity.hideNavigation()

    fun enableToolbar() = mainActivity.showToolbar()

    fun disableToolbar() = mainActivity.hideToolbar()

}