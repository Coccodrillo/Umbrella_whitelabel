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
import org.secfirst.umbrella.ui.standard.interactor.StandardMVPInteractor
import org.secfirst.umbrella.ui.standard.presenter.StandardMVPPresenter
import javax.inject.Inject

class StandardFragment : BaseFragment(), StandardMVPView {

    @Inject
    internal lateinit var presenter: StandardMVPPresenter<StandardMVPView, StandardMVPInteractor>

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
        for (blog in blogs!!) Log.e("test", blog.author)

        val realmInstance = Realm.getDefaultInstance()
        realmInstance.executeTransaction { realm ->
            val standard = Standard(1, "douglas")
            realm.insert(standard)
            val filter = realm.where(Standard::class.java).findFirst()
            Log.e("test", "salvo - " + filter!!.questionText)
        }
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}