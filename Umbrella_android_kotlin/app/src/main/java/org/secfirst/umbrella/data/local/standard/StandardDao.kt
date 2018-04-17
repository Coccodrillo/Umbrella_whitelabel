package org.secfirst.umbrella.data.local.standard

interface StandardDao {

    fun insert(standard: Standard) = standard.insert()!!

    fun delete(standard: Standard) = standard.delete()

    fun update(standard: Standard) = standard.update()

    fun loadAll(standard: Standard)

    //fun loadAll(standard: Standard) = RXSQLite.rx(select from Standard::class where (Standard_Table.is_delete.`is`(false))).queryList()
}

