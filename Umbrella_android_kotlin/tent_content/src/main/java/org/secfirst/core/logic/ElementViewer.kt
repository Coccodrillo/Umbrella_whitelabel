package org.secfirst.core.logic

import io.reactivex.Single
import org.secfirst.core.storage.Root

interface ElementViewer {

    fun init(): Single<Root>
}