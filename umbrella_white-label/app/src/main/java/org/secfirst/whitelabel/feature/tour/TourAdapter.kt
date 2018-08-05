package org.secfirst.whitelabel.feature.tour

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout
import org.secfirst.whitelabel.UmbrellaApplication
import org.secfirst.whitelabel.feature.base.view.BaseController
import org.secfirst.whitelabel.misc.BundleExt

class TourAdapter(host: Controller) : RouterPagerAdapter(host) {

    private var childView: MutableList<TourViewHolder> = mutableListOf()

    init {
        childView.add(TourViewHolder("#FF9ABE2E"))
        childView.add(TourViewHolder("#FFB83657"))
        childView.add(TourViewHolder("#FF9ABE2E"))
    }

    override fun configureRouter(router: Router, position: Int) {
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(childView[position]))
        }
    }

    override fun getCount() = childView.size
}

class TourViewHolder(bundle: Bundle) : BaseController(bundle) {

    constructor(color: String) : this(Bundle().apply {
        putSerializable(BundleExt.EXTRA_COLOR_SELECTED, color)
    })

    private val color by lazy { args.getSerializable(BundleExt.EXTRA_COLOR_SELECTED) as String }
    private val context = UmbrellaApplication.instance


    override fun onInject() {}

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) = ChildView(color).createView(AnkoContext.create(context, this, false))
}

private class ChildView(val color: String) : AnkoComponent<BaseController> {
    override fun createView(ui: AnkoContext<BaseController>) = ui.apply {
        verticalLayout {
            background = ColorDrawable(Color.parseColor(color))
        }
    }.view
}