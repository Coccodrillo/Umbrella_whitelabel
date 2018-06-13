package org.secfirst.core.logic

import org.secfirst.core.storage.Root


interface TentContent {
    fun serialize(): Root
}