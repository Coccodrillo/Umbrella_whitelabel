package org.secfirst.umbrella.core.serialize

import io.reactivex.Single
import org.secfirst.umbrella.data.storage.Root
import org.secfirst.umbrella.data.storage.TentStorageRepo
import javax.inject.Inject

class ElementAdapter @Inject constructor(private val elementSerializer: ElementSerializer,
                                         private val elementLoader: ElementLoader,
                                         private val tentRepo: TentStorageRepo) : ElementViewer {
    override fun init(): Single<Root> {
        val deserializeObj = elementSerializer.serialize(tentRepo.getElementsFile())
        return Single.just(elementLoader.load(deserializeObj, tentRepo.getLoadersFile()))
    }
}