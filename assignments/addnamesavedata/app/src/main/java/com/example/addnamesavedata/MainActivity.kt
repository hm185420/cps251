package com.example.addnamesavedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
// import the generated binding class
import com.example.addnamesavedata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // binding to MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    // variable for updating text
    private val updateText = ""

    // onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // setting the contentview to root of binding
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // updateName function to the display
        updateNameDisplay()

        // set up the button click listener using view binding
        binding.button.setOnClickListener{

            // if the text is empty, if not adds it to the list
            if (binding.editText.text.isNotEmpty()) {
                viewModel.setName(binding.editText.text.toString())

                // display the text in the textview
                binding.textView.text = viewModel.getName().joinToString( "\n")

            } else {

                // inform the user if no name was entered
                binding.textView.text = "No Name Entered"
            }

        }

    }

    // using two methods for saving and restoring the dynamic state of an activity:

    // the save on instance : called before an activity is destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(updateText, binding.textView.text.toString())
    }
    // to restore a previous state after the initialization
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // use 'savedText' for restoring state
        val savedText = savedInstanceState.getString(updateText)
        binding.textView.text = savedText
    }
    // updates the name function and checks text's emptiness
    private fun updateNameDisplay(){
        val nameList = viewModel.getName()
        if (nameList.isNotEmpty()){
            binding.textView.text = nameList.joinToString { "\n" }
        } else {
            binding.textView.text = "No names to display"
        }
    }
}