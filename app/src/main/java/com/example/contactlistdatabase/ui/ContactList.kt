package com.example.contactlistdatabase.ui

import android.app.Activity
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistdatabase.R
import com.example.contactlistdatabase.adapters.ContactItemAdapter
import com.example.contactlistdatabase.databinding.ContactListBinding
import com.example.contactlistdatabase.db.ContactDatabase
import com.example.contactlistdatabase.model.Contact
import com.example.contactlistdatabase.repository.ContactRepository

class ContactList : Fragment(), ContactItemAdapter.HandleItemClick {
    private var _binding: ContactListBinding? = null
    private val binding: ContactListBinding get() = _binding!!

    private lateinit var contactAdapter: ContactItemAdapter

    private val viewModel: ContactViewModel by viewModels {
        ContactViewModelFactory(
            requireActivity().application,
            ContactRepository(ContactDatabase(requireActivity()))
        )
    }
//    private val contactAdapter: ContactItemAdapter by lazy {
//        ContactItemAdapter()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        with(binding){
//            contactItems.layoutManager = LinearLayoutManager(requireContext())
//            contactItems.adapter = contactAdapter
//        }

        binding.contactItems.apply {
            contactAdapter = ContactItemAdapter(viewModel, this@ContactList)
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_ContactList_to_ContactForm)
        }

        binding.deleteAll.setOnClickListener {
            viewModel.deleteAllContacts()
            findNavController().navigate(R.id.action_ContactList_to_ContactForm)
            Toast.makeText(requireContext(),"All Contacts deleted!!", Toast.LENGTH_SHORT).show()
        }

        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            contactAdapter.differ.submitList(it)
        })
    }

    private fun navigateToEditForm(contactId: Contact) {
  //      val dialogBuilder = AlertDialog.Builder(requireActivity())

//        dialogBuilder.setMessage("Edit ${viewModel.contacts.value?.get(int)?.name}?")

//        var updatedText = ""
//        var updatedTodo = EditText(requireActivity())
//        updatedTodo.setText(viewModel.contacts.value?.get(int)?.name)
//        updatedTodo.requestFocus()
//        updatedTodo.setInputType(InputType.TYPE_CLASS_TEXT)
//        dialogBuilder.setView(updatedTodo)

//        dialogBuilder.setCancelable(true)
//        dialogBuilder.setPositiveButton("OK") { dialogInterface, it ->
            //updatedText = updatedTodo.text.toString()

//            val todoModel = Todo(viewModel.todos.value?.get(int)?.id, updatedText)
//            viewModel.updateTodo(todoModel)

            val bundle = bundleOf("contactId" to contactId)

            findNavController().navigate(R.id.action_ContactList_to_ContactEditForm, bundle)
       // }

//        dialogBuilder.setNegativeButton("Cancel") { dialogInterface, it ->
//            dialogInterface.dismiss()
       // }

        //viewModel.todos.value?.get(int)?.todoTask = updatedText

        //dialogBuilder.setMessage(viewModel.todos.value?.get(int)?.todoTask)
        //viewModel.todos.value?.get(int)?.todoTask?.let { viewModel.updateTodo(it) }
//        dialogBuilder.create()
//        dialogBuilder.show()
    }

    private fun showDeleteDialog(int: Int) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        dialogBuilder.setTitle("Delete contact") //viewModel.todos.value?.get(int)?.id.toString())

        dialogBuilder.setMessage("Delete ${viewModel.contacts.value?.get(int)?.name}?")

////        var updatedText = ""
////        var updatedTodo = EditText(requireActivity())
////        updatedTodo.setText(viewModel.todos.value?.get(int)?.todoTask)
////        updatedTodo.setInputType(InputType.TYPE_CLASS_TEXT)
////        dialogBuilder.setView(updatedTodo)
////
////        dialogBuilder.setCancelable(true)
        dialogBuilder.setPositiveButton("OK") { dialogInterface, it ->
            viewModel.contacts.value?.let { it1 -> viewModel.deleteContact(it1.get(int)) }
            Activity().finish()
        }
//
        dialogBuilder.setNegativeButton("Cancel") { dialogInterface, it ->
            dialogInterface.dismiss()
        }

        dialogBuilder.create()
        dialogBuilder.show()
    }

    override fun editItem(contactId: Contact) {
        navigateToEditForm(contactId)
    }

    override fun removeItem(int: Int) {
        showDeleteDialog(int)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}