package com.example.contactlistdatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactlistdatabase.R
import com.example.contactlistdatabase.databinding.ContactEditFormBinding
import com.example.contactlistdatabase.databinding.ContactFormBinding
import com.example.contactlistdatabase.db.ContactDatabase
import com.example.contactlistdatabase.model.Contact
import com.example.contactlistdatabase.repository.ContactRepository
import java.lang.Integer.parseInt

class ContactEditForm : Fragment() {
    private var _binding: ContactEditFormBinding? = null
    private val binding: ContactEditFormBinding get() = _binding!!

    lateinit var navController: NavController

    var contactId: Int = 0

    private val args by navArgs<ContactEditFormArgs>()

    private val viewModel: ContactViewModel by viewModels {
        ContactViewModelFactory(
            requireActivity().application,
            ContactRepository(ContactDatabase(requireActivity()))
        )
    }

//    override fun onCreate(savedInstanceState: Bundle?){
//        super.onCreate(savedInstanceState)
//        //contactId = requireArguments().getInt("contactId")
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContactEditFormBinding.inflate(inflater, container, false)

        contactId = args.contactId.id!!
        binding.editName.setText(args.contactId.name)
        binding.editPhone.setText(args.contactId.phone)
        binding.editAge.setText(args.contactId.age)
        binding.editOccupation.setText(args.contactId.occupation)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            updateButton.setOnClickListener {

                val name = editName.text.toString()
                val phone = editPhone.text.toString()
                val age = editAge.text.toString()
                val occupation = editOccupation.text.toString()

                if (name.isEmpty() || phone.isEmpty() || age.isEmpty() || occupation.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Please enter all the information required",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                val updatedContact = Contact(contactId, name, phone, age, occupation)

                viewModel.editContact(updatedContact)

                findNavController().navigate(R.id.action_ContactEdit_to_ContactList)
            }
        }
    }
}