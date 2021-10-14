package com.example.contactlistdatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistdatabase.adapters.ContactItemAdapter
import com.example.contactlistdatabase.databinding.ContactListBinding
import com.example.contactlistdatabase.db.ContactDatabase
import com.example.contactlistdatabase.repository.ContactRepository

class ContactList : Fragment() {
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
            contactAdapter = ContactItemAdapter(viewModel)
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            contactAdapter.differ.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}