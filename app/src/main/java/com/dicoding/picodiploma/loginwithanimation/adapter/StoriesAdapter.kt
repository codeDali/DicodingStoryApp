package com.dicoding.picodiploma.loginwithanimation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.data.response.StoriesItem
import com.dicoding.picodiploma.loginwithanimation.databinding.RvItemBinding
import com.dicoding.picodiploma.loginwithanimation.view.detail.DetailActivity

class StoriesAdapter(private val onItemClick: (StoriesItem) -> Unit): PagingDataAdapter<StoriesItem, StoriesAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story, onItemClick)
        }
    }

    class MyViewHolder (val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: StoriesItem, onItemClick: (StoriesItem) -> Unit) {
            binding.tvUsername.text = story.name
            Glide.with(binding.root.context)
                .load(story.photoUrl)
                .into(binding.ivStories)

            binding.root.setOnClickListener {
                onItemClick(story)
            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoriesItem>() {
            override fun areItemsTheSame(oldItem: StoriesItem, newItem: StoriesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: StoriesItem, newItem: StoriesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}