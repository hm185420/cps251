package com.example.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ContactListItemBinding  // Replace with your actual package name

class ContactListAdapter(private val contactItemLayout: Int) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private var contactList: List<Contact>? = null


    // bind data to views
    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val contact = contactList?.get(listPosition)

        holder.binding.contactName.text = contact?.contactName
        holder.binding.contactPhone.text = contact?.contactPhone

        // garbage
        holder.binding.garbageCan.setImageResource(R.drawable.baseline_delete_24)
        holder.binding.garbageCan.setOnClickListener {

            }
        }

    fun setContactList(contact: List<Contact>) {
        contactList = contact
        notifyDataSetChanged()
    }


}
    // create viewholder: inflates the layout for an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return contactList?.size ?: 0
    }


    class ViewHolder(val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
