package org.secfirst.umbrella.whitelabel.feature.reader.rss

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.einmalfel.earl.Feed
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.UmbrellaApplication
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.reader.DaggerReanderComponent
import org.secfirst.umbrella.whitelabel.feature.reader.interactor.ReaderBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.reader.presenter.ReaderBasePresenter
import org.secfirst.umbrella.whitelabel.feature.reader.view.ReaderView
import javax.inject.Inject

class RssController : BaseController(), ReaderView {

    @Inject
    internal lateinit var presenter: ReaderBasePresenter<ReaderView, ReaderBaseInteractor>


    override fun onInject() {
        DaggerReanderComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.onAttach(this)
        presenter.submitFetchRss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.rss_view, container, false)
        return view
    }

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

    override fun showAllRss(rss: List<Feed>) {
        rss.forEach { Log.e("test", "rss - ${it.title}") }
    }

}