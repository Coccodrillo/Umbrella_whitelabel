package org.secfirst.core.`interface`

import io.reactivex.Single
import org.secfirst.core.storage.Root

interface TentContent {
    fun getContent(): Single<Root>
}