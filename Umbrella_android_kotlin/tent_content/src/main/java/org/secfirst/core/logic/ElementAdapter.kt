package org.secfirst.core.logic

import io.reactivex.Single
import org.secfirst.core.storage.Root
import javax.inject.Inject

class ElementAdapter @Inject constructor(private val elementAdapterImp: ElementAdapterImp) : TentContent {
    override fun serialize(): Single<Root> = elementAdapterImp.serialize()
}