package com.example.finalproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactRepository (application: Application) {
    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>>?

    init {
        val db: ContactRoomDatabase? = ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        allContacts = contactDao?.getAllContact()
    }

    fun insertContact (newContact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newContact)
        }
    }
    private fun asyncInsert(contact: Contact) {
        contactDao?.insertContact(contact)
    }

    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }
    private fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    fun findContact (name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }
    //USES THE DEFERRED TO RETURN THE VALUES TO THE AWAIT OF THE FIND PRODUCT
    private fun asyncFind(name: String): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            //NOTICE THIS IS A RETURN HERE BECAUSE IT IS A SELECT QUERY IT RETURNS A VALUE
            return@async contactDao?.findContact(name)
        }

    // retrieve all contacts
    fun getAllContact(): LiveData<List<Contact>>{
        return contactDao?.getAllContact()?:MutableLiveData(emptyList())
    }

    // retrieve contacts in asc order
    fun getAllContactAsc():LiveData<List<Contact>> {
        return contactDao?.getAllContactAsc() ?: MutableLiveData(emptyList())
    }

    // retrieve contacts in des
    fun getAllContactDesc(): LiveData<List<Contact>> {
        return contactDao?.getAllContactDesc() ?: MutableLiveData(emptyList())
    }
}