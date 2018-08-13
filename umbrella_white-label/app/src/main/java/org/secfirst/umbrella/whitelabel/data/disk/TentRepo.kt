package org.secfirst.umbrella.whitelabel.data.disk

import java.io.File


interface   TentRepo {

    suspend fun fetch(): Boolean

    suspend fun getElementsFile(): List<File>

    suspend fun getLoadersFile(): List<File>

}