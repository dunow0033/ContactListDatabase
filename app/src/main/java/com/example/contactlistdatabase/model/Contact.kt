package com.example.contactlistdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val name: String,
    val phone: String,
    val age: String,
    val occupation: String
 )