package org.secfirst.umbrella.data.local.standard

interface StandardDao {

    fun insert(standard: Standard)

    fun delete(standard: Standard)

    fun update(standard: Standard)

    fun loadAll(standard: Standard)

    //fun loadAll(standard: Standard) = RXSQLite.rx(select from Standard::class where (Standard_Table.is_delete.`is`(false))).queryList()
}

