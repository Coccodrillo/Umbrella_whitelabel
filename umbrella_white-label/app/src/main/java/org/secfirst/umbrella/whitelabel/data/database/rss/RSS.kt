package org.secfirst.umbrella.whitelabel.data.database.rss

import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import org.secfirst.umbrella.whitelabel.data.database.AppDatabase
import org.secfirst.umbrella.whitelabel.data.database.BaseModel

@Table(database = AppDatabase::class, allFields = false)
class RSS(@PrimaryKey(autoincrement = true)
           var id: Long = 0,
          var title: String = "",
          var description: String = "",
          var url: String = ""
) : BaseModel()