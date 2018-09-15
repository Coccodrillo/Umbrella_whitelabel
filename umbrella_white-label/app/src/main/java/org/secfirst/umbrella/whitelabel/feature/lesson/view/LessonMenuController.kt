package org.secfirst.umbrella.whitelabel.feature.lesson.view

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.lesson_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.UmbrellaApplication
import org.secfirst.umbrella.whitelabel.data.database.content.Category
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.lesson.DaggerLessonComponent
import org.secfirst.umbrella.whitelabel.feature.lesson.interactor.LessonBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.lesson.presenter.LessonBasePresenter
import javax.inject.Inject

class LessonMenuController : BaseController(), LessonView {

    @Inject
    internal lateinit var presenter: LessonBasePresenter<LessonView, LessonBaseInteractor>
    private val lessonClick: (Category?) -> Unit = this::onLessonClicked
    private val headerClick: (Category?) -> Unit = this::onHeaderClicked
    private var sectionAdapter = SectionedRecyclerViewAdapter()

    override fun onInject() {
        DaggerLessonComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    private fun onLessonClicked(category: Category?) {
        Log.e("test", "tes")
    }

    private fun onHeaderClicked(category: Category?) {
        sectionAdapter.notifyDataSetChanged()
    }

    override fun onAttach(view: View) {
        lessonMenu?.layoutManager = LinearLayoutManager(context)
        presenter.onAttach(this)
        presenter.submitLoadAllLesson()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.lesson_view, container, false)
    }

    override fun showAllLesson(itemSections: List<LessonMenuAdapter.ItemSection>) {
        lessonMenu?.adapter = LessonMenuAdapter(itemSections, lessonClick, headerClick)
    }

    override fun getEnableBackAction() = true

    override fun getTitleToolbar(): String = context.getString(R.string.lesson_title)

}