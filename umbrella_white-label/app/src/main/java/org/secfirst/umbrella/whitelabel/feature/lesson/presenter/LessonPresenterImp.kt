package org.secfirst.umbrella.whitelabel.feature.lesson.presenter

import org.secfirst.umbrella.whitelabel.data.database.content.Category
import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.whitelabel.feature.lesson.interactor.LessonBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.lesson.view.adapter.LessonMenuAdapter
import org.secfirst.umbrella.whitelabel.feature.lesson.view.LessonView
import org.secfirst.umbrella.whitelabel.misc.AppExecutors.Companion.uiContext
import org.secfirst.umbrella.whitelabel.misc.launchSilent
import javax.inject.Inject

class LessonPresenterImp<V : LessonView, I : LessonBaseInteractor> @Inject constructor(
        interactor: I) : BasePresenterImp<V, I>(
        interactor = interactor), LessonBasePresenter<V, I> {

    override fun submitLoadAllLesson() {
        launchSilent(uiContext) {
            interactor?.let {
                val categories = it.fetchLesson()
                        .asSequence()
                        .filter { category -> category.title != "" }
                        .toList()
                getView()?.showAllLesson(toItemSection(categories))
            }
        }
    }

    private fun toItemSection(categories: List<Category>): List<LessonMenuAdapter.ItemSection> {
        val itemSections = mutableListOf<LessonMenuAdapter.ItemSection>()
        categories.forEach { category ->
            val itemGroups = mutableListOf<LessonMenuAdapter.ItemGroup>()
            category.subcategories.forEach { subcategory ->
                val itemGroup = LessonMenuAdapter.ItemGroup(subcategory.title, subcategory.id)
                itemGroups.add(itemGroup)
            }
            val itemSection = LessonMenuAdapter.ItemSection(category.title, category.resourcePath, itemGroups)
            itemSections.add(itemSection)
        }
        return itemSections
    }
}