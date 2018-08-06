package org.secfirst.whitelabel.feature.tour

import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import kotlinx.android.synthetic.main.tour_view.*
import org.secfirst.whitelabel.R
import org.secfirst.whitelabel.feature.MainActivity
import org.secfirst.whitelabel.feature.base.view.BaseController
import org.secfirst.whitelabel.feature.main.OnNavigationBottomView

class TourController : BaseController() {

    private var tourHolderViewList: MutableList<TourUI> = mutableListOf()

    private lateinit var nav: OnNavigationBottomView

    override fun onInject() {}

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

    init {
        tourHolderViewList.add(TourUI(R.color.umbrella_purple_dark, R.drawable.umbrella190, R.string.tour_slide_1_text, VISIBLE, GONE))
        tourHolderViewList.add(TourUI(R.color.umbrella_green, R.drawable.walktrough2, R.string.tour_slide_2_text, VISIBLE, GONE))
        tourHolderViewList.add(TourUI(R.color.umbrella_yellow, R.drawable.walktrough3, R.string.tour_slide_3_text, VISIBLE, GONE))
        tourHolderViewList.add(TourUI(R.color.umbrella_purple, R.drawable.walktrough4, R.string.terms_conditions, GONE, VISIBLE))
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        val tourAdapter = TourAdapter(this)
        tourAdapter.setData(tourHolderViewList)
        tourViewPager.adapter = tourAdapter
        nav = activity as MainActivity
        nav.hideBottomMenu()
        nav.hideToolbar()

        tourViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (position == tourHolderViewList.lastIndex)
                    acceptButton?.let { it.visibility = VISIBLE }
                else
                    acceptButton?.let { it.visibility = INVISIBLE }
            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.tour_view, container, false)
    }
}