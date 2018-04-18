package org.secfirst.umbrella.data.local.standard

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Standard(@PrimaryKey var id: Long = 1, @Required var questionText: String = "") : RealmObject()