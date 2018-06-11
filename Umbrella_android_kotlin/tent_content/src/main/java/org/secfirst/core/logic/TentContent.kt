package org.secfirst.core.logic

import io.reactivex.Single
import org.secfirst.core.storage.Root


interface TentContent {
    fun serialize(): Single<Root>
}