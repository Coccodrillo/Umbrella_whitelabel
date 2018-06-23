package org.secfirst.content.storage

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import java.io.File


interface   TentStorageRepo {

    fun fetch(): Single<Git>

    fun getElementsFile(): List<File>

    fun getLoadersFile(): List<File>

}