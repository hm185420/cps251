package com.example.recycleviewwithintents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewwithintents.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Binding activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Layout for the RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager

    // Adapter for the RecyclerView
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Set up  RecyclerView with LinearLayoutManager
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        // Set up  RecyclerView with adapter
        adapter = RecyclerAdapter(viewModel)
        binding.recyclerView.adapter = adapter
    }
}