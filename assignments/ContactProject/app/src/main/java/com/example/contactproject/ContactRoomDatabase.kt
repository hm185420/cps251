package com.example.contactproject

import android.content.Context
import android.provider.ContactsContract.Contacts
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Contacts::class)], version = 1 )
abstract class ContactRoomDatabase : RoomDatabase(){
    abstract fun contactDao(): ContactDao

    // next step: Singleton Pattern Implementation
    // use companion object: allows the database class to hold static functions and variables
    companion object{
        // INSTANCE: a private static variable - holds the singleton instance
        private var INSTANCE: ContactRoomDatabase? = null
        // getDatabase : a static method to get the singleton instance
        internal fun getDatabase(context: Context): ContactRoomDatabase? {
            // check INSTANCE is null
            if (INSTANCE == null){
                synchronized(ContactRoomDatabase::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ContactRoomDatabase::class.java,
                            "contact_database").build()
                    }
                }
            }
            return INSTANCE
        }
    }
}