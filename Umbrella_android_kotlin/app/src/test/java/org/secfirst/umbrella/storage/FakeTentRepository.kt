package org.secfirst.umbrella.storage

import io.reactivex.Single
import org.secfirst.umbrella.data.storage.TentStorageRepo
import java.io.File

class FakeTentRepository : TentStorageRepo {

    private val repository: List<File> = arrayListOf()

    override fun fetch(): Single<Boolean> {
        return Single.just(true)
    }

    override fun getElementsFile(): List<File> {
        return repository
    }

    override fun getLoadersFile(): List<File> {
        return repository
    }

    fun emptyRepository(): List<File> {
        return ArrayList()
    }
}

