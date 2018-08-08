package org.secfirst.whitelabel.data.database.content

import org.secfirst.whitelabel.data.Root


interface ContentRepo {

    suspend fun insertAllLessons(root: Root)

}