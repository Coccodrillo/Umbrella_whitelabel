package org.secfirst.core.storage

interface TentStorageRepo {

    fun fetch()

    fun parseFiles(): Lesson

}