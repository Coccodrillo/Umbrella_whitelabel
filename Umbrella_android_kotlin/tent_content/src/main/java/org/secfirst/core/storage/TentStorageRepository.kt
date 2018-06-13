package org.secfirst.core.storage

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import javax.inject.Inject

class TentStorageRepository @Inject constructor(private val tentStorageDao: TentStorageDao,
                                                private val tentConfig: TentConfig) : TentStorageRepo {

    override fun fetch(): Single<Git> = tentStorageDao.cloneRepository(tentConfig)

    override fun parseFiles(): Root = tentStorageDao.processElement(tentConfig)


}