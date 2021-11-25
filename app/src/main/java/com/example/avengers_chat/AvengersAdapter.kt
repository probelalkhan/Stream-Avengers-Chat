package com.example.avengers_chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.example.avengers_chat.base.BaseListAdapter
import com.example.avengers_chat.data.Avenger
import com.example.avengers_chat.databinding.ItemAvengerBinding
import com.example.avengers_chat.utils.loadAndUnveil

class AvengersAdapter: BaseListAdapter<Avenger, ItemAvengerBinding>(Comparator()) {

    class Comparator: DiffUtil.ItemCallback<Avenger>(){
        override fun areItemsTheSame(oldItem: Avenger, newItem: Avenger): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Avenger, newItem: Avenger): Boolean {
           return oldItem.id == newItem.id
        }
    }

    override fun getItemViewBinding(parent: ViewGroup) = ItemAvengerBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    override fun bindItem(binding: ItemAvengerBinding, item: Avenger) {
        binding.name.text = item.name
        binding.itemPosterPost.loadAndUnveil(item.poster, binding.itemVeilLayout)
        binding.profile.load(item.getProfileImage())
    }
}