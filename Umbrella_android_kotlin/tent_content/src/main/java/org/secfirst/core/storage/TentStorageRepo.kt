package org.secfirst.core.storage

import io.reactivex.Single
import org.eclipse.jgit.api.Git

interface TentStorageRepo {

    fun fetch(): Single<Git>

    fun parseFiles(): Root

}