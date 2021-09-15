package com.example.contactlistdatabase.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactItem (
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "number")
    var number: String,
    @ColumnInfo(name = "age")
    var age: String,
    @ColumnInfo(name = "occupation")
    var occupation: String
){
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
}