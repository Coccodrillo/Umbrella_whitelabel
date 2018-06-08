package org.secfirst.core.storage

import javax.inject.Inject

class TentStorageRepository @Inject constructor(private val tentStorageDao: TentStorageDao,
                                                private val tentConfig: TentConfig) : TentStorageRepo {
    override fun fetch() = tentStorageDao.cloneRepository(tentConfig)

    override fun parseFiles(): Lesson = tentStorageDao.tentSerialize(tentConfig)


}