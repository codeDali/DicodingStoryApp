package com.dicoding.picodiploma.loginwithanimation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.data.response.StoriesItem
import com.dicoding.picodiploma.loginwithanimation.databinding.RvItemBinding
import com.dicoding.picodiploma.loginwithanimation.view.detail.DetailActivity

class StoriesAdapter: ListAdapter<StoriesItem, StoriesAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder (val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: StoriesItem) {
            binding.tvUsername.text = story.name
            Glide.with(binding.root.context)
                .load(story.photoUrl)
                .into(binding.ivStories)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story)
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_STORY_ID, story.id)
                }
                holder.itemView.context.startActivity(intent)
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