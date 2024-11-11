package com.example.recycleviewwithintents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewwithintents.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Binding
    private lateinit var binding: ActivityMainBinding

    // Layout: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager

    // Adapter: RecyclerView
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing!
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Set up with LinearLayoutManager
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        //  RecyclerView with adapter
        adapter = RecyclerAdapter(viewModel)
        binding.recyclerView.adapter = adapter
    }
}