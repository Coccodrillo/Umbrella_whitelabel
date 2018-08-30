package org.secfirst.umbrella.whitelabel.feature.reader.rss.presenter

import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenter
import org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor.RssBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.reader.rss.view.RssView

interface RssBasePresenter<V : RssView, I : RssBaseInteractor> : BasePresenter<V, I> {

    fun submitFetchRss()

}