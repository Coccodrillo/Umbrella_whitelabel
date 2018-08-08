//package org.secfirst.whitelabel.feature.content.interactor
//
//import org.secfirst.whitelabel.data.Root
//import org.secfirst.whitelabel.data.database.content.ContentRepo
//import org.secfirst.whitelabel.data.network.ApiHelper
//import org.secfirst.whitelabel.data.storage.TentStorageRepo
//import org.secfirst.whitelabel.feature.base.interactor.BaseInteractorImp
//import org.secfirst.whitelabel.feature.tour.interactor.TourBaseInteractor
//import org.secfirst.whitelabel.serialize.ElementLoader
//import org.secfirst.whitelabel.serialize.ElementSerializer
//import javax.inject.Inject
//
//class ContentInteractorImp @Inject constructor(apiHelper: ApiHelper,
//                                               private val tentStorageRepo: TentStorageRepo,
//                                               private val contentRepo: ContentRepo,
//                                               private val elementSerializer: ElementSerializer,
//                                               private val elementLoader: ElementLoader)
//
//    : BaseInteractorImp(apiHelper), TourBaseInteractor {
//
//    override fun fetchData() = tentStorageRepo.fetch()
//
//    override fun initParser() = elementLoader.load(elementSerializer.serialize())
//
//    override fun persist(root: Root) = contentRepo.insertAllLessons(root)
//
//    override fun getAllLesson() = contentRepo.loadLessons()
//}