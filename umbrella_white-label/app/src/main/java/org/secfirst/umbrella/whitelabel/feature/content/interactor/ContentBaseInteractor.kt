//package org.secfirst.umbrella.whitelabel.feature.content.interactor
//
//import io.reactivex.Single
//import org.secfirst.umbrella.whitelabel.data.Root
//import org.secfirst.umbrella.whitelabel.data.database.content.Lesson
//import org.secfirst.umbrella.whitelabel.feature.base.interactor.BaseInteractor
//
//interface ContentBaseInteractor : BaseInteractor {
//
//    fun fetchData(): Single<Boolean>
//
//    fun persist(root: Root)
//
//    fun initParser(): Root
//
//    fun getAllLesson(): Lesson
//
//}