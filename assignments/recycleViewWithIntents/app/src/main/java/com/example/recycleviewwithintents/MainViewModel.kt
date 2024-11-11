package com.example.recycleviewwithintents

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    // Data
    private val theData = Data()

    //Randomized
    val titles: Array<String> = theData.details.randomize()
    val details: Array<String> = theData.details.randomize()
    val images: IntArray = theData.images.randomize()

    //Uses shuffle
    private fun <T> Array<T>.randomize(): Array<T> {
        this.shuffle(Random.Default)
        return this
    }

    //Uses shuffle
    private fun IntArray.randomize(): IntArray {
        this.shuffle(Random.Default)
        return this
    }
}