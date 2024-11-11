package com.example.recycleviewwithintents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleviewwithintents.databinding.ActivityySecondBinding


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the data from the intent
        val title = intent.getStringExtra("TITLE")
        val details = intent.getStringExtra("DETAILS")
        val imageId = intent.getIntExtra("IMAGE_ID", 0)

        // Set the data to the views
        binding.titleTextViews.text = title
        binding.detailsTextView.text = details
        binding.imageView.setImageResource(imageId)
    }
}