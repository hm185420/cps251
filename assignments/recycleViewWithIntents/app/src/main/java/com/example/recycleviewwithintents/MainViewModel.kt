package com.example.recycleviewwithintents

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    // Pull Data
    private val theData = Data()

    //Randomized arrays.
    val titles: Array<String> = theData.details.ramdonize()
    val details: Array<String> = theData.details.randomize()
    val images: IntArray = theData.images.randomize()

    //Uses shuffle to randomize the title and details
    private fun <T> Array<T>.randomize(): Array<T> {
        this.shuffle(Random.Default)
        return this
    }

    //Uses shuffle to randomize the image
    private fun IntArray.randomize(): IntArray {
        this.shuffle(Random.Default)
        return this
    }
}