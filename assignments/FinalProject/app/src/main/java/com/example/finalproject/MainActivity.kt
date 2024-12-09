package com.example.finalproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: ContactListAdapter? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun clearFields() {
        binding.enterContactName.setText("")
        binding.enterPhone.setText("")
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val name = binding.enterContactName.text.toString()
            val phone = binding.enterPhone.text.toString()

            if (name != "" && phone != "") {
                val contact = Contact(name, Integer.parseInt(phone).toString())
                viewModel.insertContact(contact)
                clearFields()
            }
        }
        binding.findButton.setOnClickListener {
            val nameSearch = binding.enterContactName.text.toString()
            if (nameSearch == ""){
                Toast.makeText(this, "There are no contacts that match your search", Toast.LENGTH_SHORT).show()
                //return@setOnClickListener
            }
            viewModel.findContact(
                binding.enterContactName.text.toString())
        }

        binding.ascButton.setOnClickListener {
            viewModel.getAllContactsAsc().observe(this){ contact ->
                adapter?.setContactList(contact)

            }
        }

        binding.descButton.setOnClickListener {
            viewModel.getAllContactsDesc().observe(this){ contact ->
                adapter?.setContactList(contact)
            }
        }
    }

    private fun observerSetup() {
        viewModel.getAllContacts()?.observe(this) { contact ->
            contact?.let {
                adapter?.setContactList(it)
            }
        }
        viewModel.getSearchResults().observe(this) { contact ->
            contact?.let {
                adapter?.setContactList(it)
            }
        }
    }



    private fun recyclerSetup() {
        adapter = ContactListAdapter(viewModel)
        binding.contactRecycler.layoutManager = LinearLayoutManager(this)
        binding.contactRecycler.adapter = adapter
    }

}