package org.secfirst.umbrella.whitelabel.data.disk

import java.io.File
import javax.inject.Inject

class TentRepository @Inject constructor(private val tentDao: TentDao,
                                         private val tentConfig: TentConfig) : TentRepo {

    override suspend fun getElementsFile(): List<File> = tentDao.filterBySubElement(tentConfig)

    override suspend fun getLoadersFile(): List<File> = tentDao.filterByElement(tentConfig)

    override suspend fun fetch() = tentDao.cloneRepository(tentConfig)
}