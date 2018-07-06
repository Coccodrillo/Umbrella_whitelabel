package org.secfirst.umbrella.data.storage

import io.reactivex.Single
import java.io.File


interface   TentStorageRepo {

    fun fetch(): Single<Boolean>

    fun getElementsFile(): List<File>

    fun getLoadersFile(): List<File>

}