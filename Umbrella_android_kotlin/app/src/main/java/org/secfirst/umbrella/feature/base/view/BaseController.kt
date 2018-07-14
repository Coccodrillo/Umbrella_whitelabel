package org.secfirst.umbrella.feature.base.view

import android.view.View
import com.bluelinelabs.conductor.Controller
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*

abstract class BaseController : Controller(), LayoutContainer {

    init {
        inject()
    }

    private fun inject() = onInject()

    protected abstract fun onInject()

    override val containerView: View?
        get() = view

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        clearFindViewByIdCache()
    }
}
