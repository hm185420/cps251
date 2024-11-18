package com.example.coruntinesproject

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coruntinesproject.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    // utilize viewmodel: declare binding variable
    private lateinit var mainViewModel: MainViewModel

    // binging: for view binding, direct access
    private lateinit var binding: ActivityMainBinding
    private var adapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(binding.root)

        // initializes the MainViewModel
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Setup RecyclerView and observe ViewModel
        setupRecyclerView()
        observeViewModel()

        // Set up the RecyclerView
        fun setupRecyclerView() {
            binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
            adapter = RecyclerAdapter()
            binding.myRecyclerView.adapter = adapter
        }

        // Observe ViewModel
        fun observeViewModel() {
            mainViewModel.nameList.observe(this) { nameList ->
                // Update the RecyclerView adapter
                adapter?.updateData(nameList, mainViewModel.delayList.value ?: mutableListOf())
            }
        }

        // Coroutine is launched
        fun launchCoroutine(view: View) {
            // Pull Name
            val name = binding.addNameText.text.toString()

            //Check If name is empty
            if (name.isBlank()) {
                return
            }

            // Clear Text
            binding.addNameText.text.clear()
            // Add Name
            mainViewModel.addName(name)
        }
    }

    private fun observeViewModel() {
        TODO("Not yet implemented")
    }

    private fun setupRecyclerView() {
        TODO("Not yet implemented")
    }
}

