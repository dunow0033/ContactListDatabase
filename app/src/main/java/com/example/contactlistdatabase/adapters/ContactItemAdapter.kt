package com.example.contactlistdatabase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistdatabase.databinding.ContactItemBinding
import com.example.contactlistdatabase.db.entities.ContactItem
import com.example.contactlistdatabase.model.Contact
import com.example.contactlistdatabase.ui.ContactViewModel

class ContactItemAdapter(
//    var contacts: List<ContactItem>,
//    private val viewModel: ContactViewModel
) : RecyclerView.Adapter<ContactItemAdapter.ContactViewHolder>() {

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
        holder.bind(differ.currentList[position])
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

    class ContactViewHolder(val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.name.text = contact.name
            binding.number.text = contact.number
            binding.age.text = contact.age
            binding.occupation.text = contact.job
        }
    }
}