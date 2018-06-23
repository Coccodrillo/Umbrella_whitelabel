package org.secfirst.umbrella.feature.content.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.Lesson
import org.secfirst.umbrella.feature.base.view.BaseFragment
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.presenter.ContentBasePresenter
import javax.inject.Inject

class ContentFragment : BaseFragment(), ContentBaseView {

    override fun getData(lesson: Lesson) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    internal lateinit var presenter: ContentBasePresenter<ContentBaseView, ContentBaseInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_standard, container, false)

    companion object {
        fun newInstance(): ContentFragment = ContentFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setUp() {
        presenter.manageContent()
    }

    override fun downloadContent() {
    }

}