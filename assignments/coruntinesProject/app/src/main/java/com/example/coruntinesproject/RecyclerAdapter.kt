package com.example.coruntinesproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coruntinesproject.databinding.CardLayoutBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    private var names: MutableList<String> = mutableListOf()
    private var delays: MutableList<Long> = mutableListOf()


    // Viewholder holds the view for each item using View Binding
    // explain how each item should look
    inner class ViewHolder (private val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)  {
        // Function to bind data
        fun bind(name: String, delay: Long) {
            // Set the text
            binding.displayText.text = "The name is $name and the delay was $delay milliseconds."
        }
    }

    // Methods to dynamically update the data
    // This is just to show what I can do not required to create a reyvleview list
    // Update the adapter data then refresh the RecyclerView
    fun updateData(newNames: MutableList<String>, newDelays: MutableList<Long>) {
        names = newNames // Update names list
        delays = newDelays // Update delays list
        notifyDataSetChanged() // Notify adapter
    }

   // Create new views invoked by the layout manager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        // Inflate the layout for each item
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardLayoutBinding.inflate(inflater, parent, false)
        // Return a new ViewHolder instance
        return ViewHolder(binding)
    }
    // Override function to bind data to each ViewHolder
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        // Get the name and delay
        val name = names[position]
        val delay = delays[position]
        // Bind the data
        holder.bind(name, delay)
    }
    // Get the total count of items
    override fun getItemCount(): Int {
        return names.size
    }
}
