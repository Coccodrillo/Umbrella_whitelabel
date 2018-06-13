package org.secfirst.core.view

import io.reactivex.Single
import org.secfirst.core.storage.Root

interface ElementViewer {

    fun init(): Single<Root>
}