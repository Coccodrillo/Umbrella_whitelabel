package org.secfirst.core.logic

import org.secfirst.core.storage.Root
import org.secfirst.core.storage.TypeFile
import javax.inject.Inject

class ElementAdapter @Inject constructor(private val elementAdapterImp: ElementAdapterImp,
                                         private val segmentAdapter: SegmentAdapter) : TentContent {
    override fun serialize(): Root {
        return elementAdapterImp.serialize(TypeFile.SEGMENT, Root())
    }
}