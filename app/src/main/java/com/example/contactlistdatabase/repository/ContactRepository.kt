package com.example.contactlistdatabase.repository

import com.example.contactlistdatabase.db.ContactDatabase
import com.example.contactlistdatabase.model.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(val db : ContactDatabase) {

    suspend fun addContact(contact: Contact){
        db.getContactDao().insertContact(contact)
    }

    suspend fun getContacts(): Flow<List<Contact>> {
        return db.getContactDao().getContacts()
    }
}