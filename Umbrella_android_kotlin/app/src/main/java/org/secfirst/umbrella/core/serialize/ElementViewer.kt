package org.secfirst.umbrella.core.serialize

import io.reactivex.Single
import org.secfirst.umbrella.data.storage.Root


interface ElementViewer {

    fun init(): Single<Root>
}