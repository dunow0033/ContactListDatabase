package com.example.contactlistdatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.contactlistdatabase.R
import com.example.contactlistdatabase.adapters.ContactItemAdapter
import com.example.contactlistdatabase.databinding.ContactFormBinding
import com.example.contactlistdatabase.db.ContactDatabase
import com.example.contactlistdatabase.db.entities.ContactItem
import com.example.contactlistdatabase.model.Contact
import com.example.contactlistdatabase.repository.ContactRepository

class ContactForm : Fragment() {
    private var _binding: ContactFormBinding? = null
    private val binding: ContactFormBinding get() = _binding!!

    private val viewModel: ContactViewModel by viewModels {
        ContactViewModelFactory(
            requireActivity().application,
            ContactRepository(ContactDatabase(requireActivity()))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonFirst.setOnClickListener {

                val name = etName.text.toString()
                val phone = etPhone.text.toString()
                val age = etAge.text.toString()
                val occupation = etOccupation.text.toString()

                if (name.isEmpty() || phone.isEmpty() || age.isEmpty() || occupation.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Please enter all the information required",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //val contact = Contact(name, phone, age, occupation)

                viewModel.addContact(Contact(name = name, phone = phone, age = age, occupation = occupation))

                findNavController().navigate(R.id.action_ContactForm_to_ContactList)
            }
        }
    }
}