package org.secfirst.whitelabel.feature.tour.presenter

import org.secfirst.whitelabel.feature.base.presenter.BasePresenter
import org.secfirst.whitelabel.feature.tour.interactor.TourBaseInteractor
import org.secfirst.whitelabel.feature.tour.view.TourView

interface TourBasePresenter<V : TourView, I : TourBaseInteractor> : BasePresenter<V, I> {

    fun manageContent()

}