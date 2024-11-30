package com.example.contactproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ContactRepository (application: Application) {
    // variables
    val searchResult = MutableLiveData<List<Contact>>()
    // access to DAO methods for databse operations
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    // observe all contacts in the database using LiveData
    val allContacts: LiveData<List<Contact>>?

    // set up the database and DAO references 
    init {
        val db: ContactRoomDatabase?= ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        allContacts = contactDao?.getAllContacts()
    }

    // inserts asynchronously
    fun insertContact(newContact: Contact){
        // runs on the IO thread
        coroutineScope.launch(Dispatchers.IO){
            asyncInsert(newContact)
        }
    }

    // delecte contact from database
    fun deleteContact(id:Int){
        coroutineScope.launch(Dispatchers.IO){
            asyncDelete(id)
        }
    }

    private fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    private fun asyncInsert(newContact: Contact) {
        contactDao?.insertContact(newContact)
    }

    // findcontact by name
    fun findContact (name: String){
        coroutineScope.launch(Dispatchers.Main){ // update on the main thread
            searchResult.value = asyncFind(name).await() // runs query on IO thread
        }
    }

    private fun asyncFind(name: String):
    Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) { // execute query on IO thread
            return@async contactDao?.findContact(name)
    }

}