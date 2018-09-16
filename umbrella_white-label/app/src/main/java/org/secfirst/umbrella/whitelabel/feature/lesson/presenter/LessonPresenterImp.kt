package org.secfirst.umbrella.whitelabel.feature.lesson.presenter

import org.secfirst.umbrella.whitelabel.data.Difficult
import org.secfirst.umbrella.whitelabel.data.Difficult.Companion.ADVANCED
import org.secfirst.umbrella.whitelabel.data.Difficult.Companion.BEGINNER
import org.secfirst.umbrella.whitelabel.data.Difficult.Companion.EXPERT
import org.secfirst.umbrella.whitelabel.data.database.content.Category
import org.secfirst.umbrella.whitelabel.data.database.content.Subcategory
import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.whitelabel.feature.lesson.interactor.LessonBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.lesson.view.LessonView
import org.secfirst.umbrella.whitelabel.feature.lesson.view.adapter.ItemGroup
import org.secfirst.umbrella.whitelabel.feature.lesson.view.adapter.ItemSection
import org.secfirst.umbrella.whitelabel.misc.AppExecutors.Companion.uiContext
import org.secfirst.umbrella.whitelabel.misc.launchSilent
import javax.inject.Inject

class LessonPresenterImp<V : LessonView, I : LessonBaseInteractor> @Inject constructor(
        interactor: I) : BasePresenterImp<V, I>(
        interactor = interactor), LessonBasePresenter<V, I> {

    override fun submitLessonSelect(lessonSelected: ItemGroup) {

        launchSilent(uiContext) {
            interactor?.let {
                val category = it.fetchCategoryBy(lessonSelected.id)
                getView()?.showSelectDifficult(toDifficult(category))
            }
        }
    }


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

    private fun toDifficult(category: Subcategory): MutableList<Difficult> {
        val difficulties = mutableListOf<Difficult>()
        val sortedList = category.children.sortedWith(compareBy { it.index })
        sortedList.forEach { subCategory ->
            when (subCategory.index) {
                BEGINNER -> difficulties.add(Difficult(subCategory.title, subCategory.description, "#87BD34", category.title))
                ADVANCED -> difficulties.add(Difficult(subCategory.title, subCategory.description, "#F3BC2B", category.title))
                EXPERT -> difficulties.add(Difficult(subCategory.title, subCategory.description, "#B83657", category.title))
                else -> {
                    difficulties.add(Difficult(subCategory.title, subCategory.description, "#B83657", category.title))
                }
            }
        }
        return difficulties
    }

    private fun toItemSection(categories: List<Category>): List<ItemSection> {
        val itemSections = mutableListOf<ItemSection>()
        categories.forEach { category ->
            val itemGroups = mutableListOf<ItemGroup>()
            category.subcategories.forEach { subcategory ->
                val itemGroup = ItemGroup(subcategory.title, subcategory.id)
                itemGroups.add(itemGroup)
            }
            val itemSection = ItemSection(category.title, category.resourcePath, itemGroups)
            itemSections.add(itemSection)
        }
        return itemSections
    }
}