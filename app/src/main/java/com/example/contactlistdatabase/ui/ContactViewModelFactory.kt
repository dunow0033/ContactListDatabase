package com.example.contactlistdatabase.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactlistdatabase.repository.ContactRepository
import java.lang.IllegalArgumentException

class ContactViewModelFactory(
    private val app: Application,
    private val repo: ContactRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(app, repo) as T
        } else {
            throw IllegalArgumentException("instance of ContactViewModel cannot be created")
        }
    }
}