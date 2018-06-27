package org.secfirst.umbrella.data

import com.raizlabs.android.dbflow.annotation.*
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.SQLite
import org.secfirst.umbrella.data.database.AppDatabase

@Table(database = AppDatabase::class)
data class Father(
        @PrimaryKey(autoincrement = true)
        @Column
        var id: Long = 0,
        @Column
        var name: String = "non",
        @ForeignKey(onUpdate = ForeignKeyAction.CASCADE,
                onDelete = ForeignKeyAction.CASCADE,
                stubbedRelationship = true)
        @ForeignKeyReference(foreignKeyColumnName = "id", columnName = "son_id")
        var container: Father? = null,
        var children: MutableList<Father> = arrayListOf()) : BaseRXModel() {


    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "children")
    fun oneToManyItems(): MutableList<Father> {
        if (children.isEmpty()) {
            children = SQLite.select()
                    .from(Father::class.java)
                    .where(Father_Table.container_id.eq(id))
                    .queryList()
        }
        return children
    }
}

//@Table(database = AppDatabase::class)
//data class Son(
//        @PrimaryKey(autoincrement = true)
//        @Column
//        var id: Long = 0,
//        @Column
//        var name: String = "non",
//        @Column
//        @ForeignKey(onUpdate = ForeignKeyAction.CASCADE,
//                onDelete = ForeignKeyAction.CASCADE,
//                stubbedRelationship = true)
//        @ForeignKeyReference(foreignKeyColumnName = "id", columnName = "son_id")
//        var container: Father? = null,
//        @Column
//        var age: Int = 0) : BaseRXModel()