package org.secfirst.umbrella.data.storage

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import java.io.File
import javax.inject.Inject

class TentStorageRepository @Inject constructor(private val tentStorageDao: TentStorageDao,
                                                private val tentConfig: TentConfig) : TentStorageRepo {

    override fun getElementsFile(): List<File> = tentStorageDao.filterBySubElement(tentConfig)

    override fun getLoadersFile(): List<File> = tentStorageDao.filterByElement(tentConfig)

    override fun fetch(): Single<Git> = tentStorageDao.cloneRepository(tentConfig)
}