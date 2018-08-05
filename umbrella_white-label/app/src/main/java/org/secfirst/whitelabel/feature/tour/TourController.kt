package org.secfirst.whitelabel.feature.tour

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.tour_view.*
import org.secfirst.whitelabel.R
import org.secfirst.whitelabel.feature.base.view.BaseController

class TourController : BaseController() {

    private lateinit var tourAdapter: TourAdapter

    override fun onInject() {}

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

    override fun onAttach(view: View) {
        super.onAttach(view)
        tourViewPage.adapter = TourAdapter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.tour_view, container, false)
        return view
    }

    override fun onDestroyView(view: View) {
        activity?.let {
            if (it.isChangingConfigurations)
                tourViewPage.adapter = null
        }
        super.onDestroyView(view)
    }
}