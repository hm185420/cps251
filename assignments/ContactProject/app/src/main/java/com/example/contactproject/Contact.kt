package com.example.contactproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity : denote that this class is an entity in a Room database
@Entity(tableName = "contacts")
class Contact {
    // @PrimaryKey marks id as the primary key
    @PrimaryKey(autoGenerate = true)

    // create table with following fields: contactId, contactName, contactPhone
    @ColumnInfo(name = "contactId")
    var id: Int = 0

    @ColumnInfo(name = "contactName")
    var contactName: String? = null

    @ColumnInfo(name = "contactPhone")
    var contactPhone: String? = null

    // default constructor : required by Room to instantiate the entity
    constructor()

    // partial constructor : initializes a "contact" object without an id
    constructor(contactsName: String, contactPhone: String){
        this.contactName = contactsName
        this.contactPhone = contactPhone
    }
}