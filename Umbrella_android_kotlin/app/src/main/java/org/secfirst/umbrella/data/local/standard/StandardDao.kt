package org.secfirst.umbrella.data.local.standard

import io.realm.Realm

interface StandardDao {

    fun insert(standard: Standard) = Realm.getDefaultInstance().executeTransactionAsync { realm -> realm.insertOrUpdate(standard) }

    fun update(standard: Standard) = Realm.getDefaultInstance().executeTransactionAsync { realm -> realm.insertOrUpdate(standard) }

    fun getStandard() = Realm.getDefaultInstance().where(Standard::class.java).findFirst()


}

