package org.secfirst.core.storage

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import org.secfirst.core.utils.TentConfig
import java.io.File
import javax.inject.Inject

class TentStorageRepository @Inject constructor(private val tentStorageDao: TentStorageDao,
                                                private val tentConfig: TentConfig) : TentStorageRepo {

    override fun getElementsFile(): List<File> = tentStorageDao.getSerializesFile(tentConfig)

    override fun getLoadersFile(): List<File> = tentStorageDao.getLoadersFile(tentConfig)

    override fun fetch(): Single<Git> = tentStorageDao.cloneRepository(tentConfig)
}