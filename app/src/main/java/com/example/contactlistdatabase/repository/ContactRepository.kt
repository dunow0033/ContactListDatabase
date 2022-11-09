package com.example.contactlistdatabase.repository

import com.example.contactlistdatabase.db.ContactDatabase
import com.example.contactlistdatabase.model.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(val db : ContactDatabase) {

    suspend fun addContact(contact: Contact){
        db.getContactDao().insertContact(contact)
    }

    suspend fun editContact(contact: Contact){
        db.getContactDao().editContact(contact)
    }

    suspend fun deleteContact(contact: Contact){
        db.getContactDao().deleteContact(contact)
    }

    val getContacts: Flow<List<Contact>> = db.getContactDao().getContacts()
}