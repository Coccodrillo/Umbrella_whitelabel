package org.secfirst.content.serialize

import org.secfirst.content.storage.Root
import org.secfirst.content.storage.TentStorageRepo
import javax.inject.Inject

class ElementAdapter @Inject constructor(private val elementSerializer: ElementSerializer,
                                         private val elementLoader: ElementLoader,
                                         private val tentRepo: TentStorageRepo) : ElementViewer {
    override fun init(): Root {
        val deserializeObj = elementSerializer.serialize(tentRepo.getElementsFile())
        return elementLoader.load(deserializeObj, tentRepo.getLoadersFile())
    }
}