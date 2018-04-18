package org.secfirst.umbrella.data.local.standard


interface StandardDao {

    fun insert(standard: Standard)
    fun delete(standard: Standard)
    fun update(standard: Standard)
    fun loadAll()
}

