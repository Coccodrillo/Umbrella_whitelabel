package org.secfirst.umbrella.whitelabel.feature.reader.rss.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.UmbrellaApplication
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.reader.rss.DaggerReanderComponent
import org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor.RssBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.reader.rss.presenter.RssBasePresenter
import javax.inject.Inject

class RssController : BaseController() {


    @Inject
    internal lateinit var presenter: RssBasePresenter<RssView, RssBaseInteractor>


    override fun onInject() {
        DaggerReanderComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.feed_view, container, false)
        presenter.submitFetchRss()
        return view
    }

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

}