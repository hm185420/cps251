package com.example.finalproject

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts")
class Contact {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contactId")
    var id: Int = 0

    @ColumnInfo(name = "contactName")
    var contactName: String? = null

    @ColumnInfo(name = "contactPhone")
    var contactPhone: String? = null

    constructor() {}

    constructor(contactName: String, contactPhone: String ) {
        this.contactName = contactName
        this.contactPhone = contactPhone
    }
}