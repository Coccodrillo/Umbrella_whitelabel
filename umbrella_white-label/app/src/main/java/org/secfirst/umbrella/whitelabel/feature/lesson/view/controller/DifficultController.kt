package org.secfirst.umbrella.whitelabel.feature.lesson.view.controller

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.difficult_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.data.Difficult
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.lesson.view.LessonView
import org.secfirst.umbrella.whitelabel.feature.lesson.view.adapter.DifficultAdapter

class DifficultController(bundle: Bundle) : BaseController(bundle), LessonView {

    private val difficultClick: (Difficult) -> Unit = this::onDifficultClick
    private val difficuties by lazy { args.getParcelableArray(EXTRA_SELECTED_DIFFICULTY) }
    private val difficultAdapter: DifficultAdapter = DifficultAdapter(difficultClick)

    constructor(difficulties: List<Difficult>) : this(Bundle().apply {
        putParcelableArray(EXTRA_SELECTED_DIFFICULTY, difficulties.toTypedArray())
    })

    companion object {
        const val EXTRA_SELECTED_DIFFICULTY = "selected_difficulty"
    }

    private fun onDifficultClick(difficult: Difficult) {

    }

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(view: View) {
        difficultRecyclerView?.let {
            it.layoutManager = LinearLayoutManager(context)
            difficultAdapter.addAll(difficuties as Array<Difficult>)
            it.adapter = difficultAdapter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.difficult_view, container, false)
    }

    override fun getEnableBackAction() = true

    override fun getTitleToolbar() = ""

    override fun onInject() {}

}