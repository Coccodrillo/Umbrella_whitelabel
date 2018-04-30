package org.secfirst.umbrella.data.local.standard

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import org.secfirst.umbrella.data.local.AppDatabase


@Table(database = AppDatabase::class, allFields = true)
open class Standard(@PrimaryKey(autoincrement = true) var id: Long = 1, @Column var questionText: String = "") : BaseRXModel()

