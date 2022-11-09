package com.example.contactlistdatabase.db

import androidx.room.*
import com.example.contactlistdatabase.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getContacts() : Flow<List<Contact>>

    @Update
    suspend fun editContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}