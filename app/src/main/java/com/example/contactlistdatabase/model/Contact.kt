package com.example.contactlistdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "age")
    val age: String,
    @ColumnInfo(name = "occupation")
    val occupation: String
 )