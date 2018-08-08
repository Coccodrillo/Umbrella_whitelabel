package org.secfirst.whitelabel.feature.tour.view

import org.secfirst.whitelabel.feature.base.view.BaseView

interface TourView : BaseView {

    fun downloadContentCompleted(res: Boolean)

    fun downloadContentInProgress()
}