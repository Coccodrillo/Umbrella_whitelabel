package org.secfirst.umbrella.ui.standard.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.local.standard.Standard
import org.secfirst.umbrella.data.network.Blog
import org.secfirst.umbrella.ui.base.view.BaseFragment
import org.secfirst.umbrella.ui.standard.interactor.StandardBaseInteractor
import org.secfirst.umbrella.ui.standard.presenter.StandardBasePresenter
import javax.inject.Inject

class StandardFragment : BaseFragment(), StandardBaseView {

    @Inject
    internal lateinit var presenter: StandardBasePresenter<StandardBaseView, StandardBaseInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_standard, container, false)


    companion object {
        fun newInstance(): StandardFragment = StandardFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        presenter.onViewPrepared()
    }

    override fun displayBlogList(blogs: List<Blog>?) {

        //Network's test
        for (blog in blogs!!) Log.e("test", blog.author)

        //Insert db
        presenter.onValidateInsertStandard(Standard(2, "douglas"))

        //get object in database
        presenter.getData()

    }

    override fun displayData(standard: Standard?) {
        Log.i("test", "saved - " + standard!!.questionText)
    }


    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}