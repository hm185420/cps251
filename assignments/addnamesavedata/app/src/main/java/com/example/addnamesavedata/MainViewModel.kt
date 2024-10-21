package com.example.addnamesavedata

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    // the names into a list
    private val nameList = ArrayList<String>()

    // function adds the name
    fun setName(value: String){
        nameList.add(value)
    }
    // function that pulls the name list
    fun getName(): List<String> {
        return nameList.toList()
    }


}