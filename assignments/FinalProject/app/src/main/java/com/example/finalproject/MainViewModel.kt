package com.example.finalproject

import android.app.Application
import android.provider.ContactsContract.Contacts
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository = ContactRepository(application)
    private val allContacts: LiveData<List<Contact>>? = repository.allContacts
    private val searchResults: MutableLiveData<List<Contact>> = repository.searchResults

    fun insertContact(contact: Contact) {
        repository.insertContact(contact)
    }

    fun findContact(name: String) {
        repository.findContact(name)
    }
    fun deleteContact(id: Int) {
        repository.deleteContact(id)
    }
    fun getAllContactsAsc(): LiveData<List<Contact>> {
        return repository.getAllContactAsc()
    }
    fun getAllContactsDesc(): LiveData<List<Contact>> {
        return repository.getAllContactDesc()
    }

    fun getAllContacts(): LiveData<List<Contact>>? {
        return allContacts
    }

    fun getSearchResults(): MutableLiveData<List<Contact>> {
        return searchResults
    }
}