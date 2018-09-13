package org.secfirst.umbrella.whitelabel.feature.lesson.view

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.lesson_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.UmbrellaApplication
import org.secfirst.umbrella.whitelabel.data.database.content.Category
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.lesson.DaggerLessonComponent
import org.secfirst.umbrella.whitelabel.feature.lesson.interactor.LessonBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.lesson.presenter.LessonBasePresenter
import javax.inject.Inject

class MenuLessonController : BaseController(), LessonView {

    @Inject
    internal lateinit var presenter: LessonBasePresenter<LessonView, LessonBaseInteractor>
    private val lessonClick: (Category) -> Unit = this::onLessonClicked
    private val headerClick: (Int) -> Unit = this::onHeaderClicked
    private val lessonAdapter = MenuLessonAdapter(lessonClick, headerClick)

    override fun onInject() {
        DaggerLessonComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    private fun onHeaderClicked(position: Int) {
        if (lessonAdapter.isSectionExpanded(lessonAdapter.getSectionIndex(position))) {
            lessonAdapter.collapseSection(lessonAdapter.getSectionIndex(position))
        } else {
            lessonAdapter.expandSection(lessonAdapter.getSectionIndex(position))
        }
    }

    private fun onLessonClicked(category: Category) {

    }

    override fun onAttach(view: View) {
        lessonMenu?.layoutManager = LinearLayoutManager(context)
        presenter.onAttach(this)
        presenter.submitLoadAllLesson()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.lesson_view, container, false)
    }

    override fun showAllLesson(categories: List<Category>) {
        lessonAdapter.addAll(categories)
        lessonMenu?.adapter = lessonAdapter
        lessonAdapter.collapseAllSections()

    }

    override fun getEnableBackAction() = true

    override fun getTitleToolbar() = context.getString(R.string.lesson_title)

}