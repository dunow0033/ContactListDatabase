package com.example.contactlistdatabase.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
//import com.example.contactlistdatabase.db.entities.Contact
import com.example.contactlistdatabase.model.Contact
import com.example.contactlistdatabase.repository.ContactRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContactViewModel(
       app: Application,
       private val repo: ContactRepository
) :
    AndroidViewModel(app) {

    private var _contacts: MutableLiveData<List<Contact>> = MutableLiveData()
    val contacts: LiveData<List<Contact>> = _contacts

    init {
        getAllContacts()
    }

    fun getAllContacts() {
        viewModelScope.launch {
            repo.getContacts.collect {
                _contacts.postValue(it)
            }
        }
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            repo.addContact(contact)
        }
    }

    fun editContact(contact: Contact) {
        viewModelScope.launch {
            repo.editContact(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repo.deleteContact(contact)
        }
    }

    fun deleteAllContacts() {
        viewModelScope.launch {
            repo.deleteAllContacts()
        }
    }
}
