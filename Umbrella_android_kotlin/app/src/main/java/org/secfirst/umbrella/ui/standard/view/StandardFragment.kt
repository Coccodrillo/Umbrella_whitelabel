package org.secfirst.umbrella.ui.standard.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
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

        val standards = ArrayList<Standard>()
        for (index in blogs?.indices!!) {
            Log.d("test", blogs[index].author)
            standards.add(Standard(index.toLong(), blogs[index].author!!))
        }

        Realm.getDefaultInstance().executeTransactionAsync { realm ->
            realm.insert(Standard(1, "douglas"))
            val item = realm.where(Standard::class.java).findFirst()

            Log.e("test", "foi inserido - " + item!!.questionText)
        }
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}