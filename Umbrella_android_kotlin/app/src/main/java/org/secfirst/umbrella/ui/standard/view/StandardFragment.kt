package org.secfirst.umbrella.ui.standard.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.async
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.local.AppDatabase
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

        FlowManager.getDatabase(AppDatabase.NAME)
                .beginTransactionAsync(ProcessModelTransaction.Builder<Standard>(
                        ProcessModelTransaction.ProcessModel<Standard> { model, wrapper ->
                            model.save()
                            val employees = SQLite.select().from<Standard>(Standard::class.java).queryList()
                            Log.e("Test", "second count=" + employees.size)
                        }).addAll(standards).build())
                .error(Transaction.Error { transaction, error -> Log.e("test", "erro ---" + error.message) })
                .success(Transaction.Success { Log.e("test", "sucess") }).build().execute()


        standards[2].save().async()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}