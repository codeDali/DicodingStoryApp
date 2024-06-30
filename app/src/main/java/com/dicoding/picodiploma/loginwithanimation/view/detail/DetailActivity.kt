package com.dicoding.picodiploma.loginwithanimation.view.detail

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.R
import com.dicoding.picodiploma.loginwithanimation.data.response.StoriesItem
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.loginwithanimation.view.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val story: StoriesItem? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_STORY_ID, StoriesItem::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_STORY_ID)
        }

        if (story != null) {
            fetchStories(story)
        } else {
            Toast.makeText(this, "No story data available", Toast.LENGTH_SHORT).show()
            finish()
        }


        binding.placeholder.text = getString(R.string.deskripsi)
    }

    private fun fetchStories(story: StoriesItem) {
            binding.apply {
                binding.tvusernameDetail.text = story.name
                binding.tvDescriptionDetail.text = story.description
                binding.tvTimeDetail.text = story.createdAt
            }
            Glide.with(this)
                .load(story.photoUrl)
                .into(binding.ivDetail)

    }

    companion object {
        const val EXTRA_STORY_ID = "extra_story_id"
    }
}