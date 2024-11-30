package com.example.contactproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao // Room DAO : abstract methods for interacting with the database
interface ContactDao {
    @Insert
    fun insertContact(contact: Contact)

    // ALL Query from the assignments
    // method : search for a contact by its name
    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name || '%'")
    fun findContact(name: String): List<Contact>

    // delete by ID
    @Query("DELETE FROM contacts WHERE contactId = :id")
    fun deleteContact(id: Int)

    // retrieve all contacts from the database
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    //THIS DOES THE ASC SORT FROM THE DATABASE
    @Query("SELECT * FROM contacts ORDER BY contactName ASC")
    fun getAllContactsAsc(): LiveData<List<Contact>>

    //THIS DOES THE DESC SORT FROM THE DATABASE
    @Query("SELECT * FROM contacts ORDER BY contactName DESC")
    fun getAllContactsDesc(): LiveData<List<Contact>>
}