package org.secfirst.core.logic

import io.reactivex.Single
import org.secfirst.core.storage.Root
import org.secfirst.core.storage.TentStorageRepo
import org.secfirst.core.view.ElementViewer
import javax.inject.Inject

class ElementAdapter @Inject constructor(private val elementSerializer: ElementSerializer,
                                         private val elementLoader: ElementLoader,
                                         private val tentRepo: TentStorageRepo) : ElementViewer {
    override fun init(): Single<Root> {
        val root = elementSerializer.serialize(tentRepo.getElementsFile())
        return Single.just(elementLoader.load(root, tentRepo.getLoadersFile()))
    }
}