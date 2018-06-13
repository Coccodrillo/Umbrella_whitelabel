package org.secfirst.core.logic

import io.reactivex.Single
import org.secfirst.core.storage.Root
import javax.inject.Inject

class ElementAdapter @Inject constructor(private val elementSerializer: ElementSerializer,
                                         private val elementLoader: ElementLoader) : ElementViewer {
    override fun init(): Single<Root> {
        val root = elementSerializer.serialize()
        return Single.just(elementLoader.load(root))
    }


}