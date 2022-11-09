package com.example.contactlistdatabase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
//import androidx.navigation.fragment.NavHostFragment.findNavController
//import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistdatabase.R
import com.example.contactlistdatabase.databinding.ContactItemBinding
//import com.example.contactlistdatabase.db.entities.Contact
import com.example.contactlistdatabase.model.Contact
import com.example.contactlistdatabase.ui.ContactViewModel

class ContactItemAdapter(
    private val viewModel: ContactViewModel,
    clickListener: HandleItemClick
) : RecyclerView.Adapter<ContactItemAdapter.ContactViewHolder>() {

    private var clickListener: HandleItemClick = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ContactItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = differ.currentList[position]

        holder.binding.apply {
            name.text = contact.name
            age.text = contact.age
            phone.text = contact.phone
            occupation.text = contact.occupation

            holder.binding.ivDelete.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view: View) {
                    clickListener.removeItem(holder.adapterPosition)
                }
            })

            holder.binding.ivEdit.setOnClickListener(object : View.OnClickListener {
                //viewModel.editContact(contact)

                override fun onClick(view: View) {
                    //clickListener.editItem(holder.adapterPosition)
                    clickListener.editItem(contact)
                    //findNavController().navigate(R.id.action_ContactList_to_ContactEditForm, position)
                }
            })
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<Contact>(){
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    class ContactViewHolder(val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface HandleItemClick {
        fun editItem(contact: Contact)
        fun removeItem(int: Int)
    }
}