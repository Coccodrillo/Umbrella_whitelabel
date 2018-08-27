package org.secfirst.umbrella.whitelabel.data.database.reader.rss

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import org.secfirst.umbrella.whitelabel.data.database.AppDatabase
import org.secfirst.umbrella.whitelabel.data.database.BaseModel

@Table(database = AppDatabase::class)
data class RSS(@PrimaryKey(autoincrement = true)
               var id: Long = 0,
               @Column
               var title: String = "",
               @Column
               var description: String = "",
               @Column
               var url: String = "",
               var items: List<Item> = listOf()
) : BaseModel()

data class Item(var link: String = "")