package com.dicoding.mistoriyy.Detail



import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.mistoriyy.R
import com.dicoding.mistoriyy.databinding.ActivityDetailBinding
import com.dicoding.mistoriyy.data.di.Injection
import com.dicoding.mistoriyy.data.di.StoriyResult
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModelVactory: DetailViewModelFactory = DetailViewModelFactory.getInstance(application, Injection.detailSutoriRepository(this))
        detailViewModel = ViewModelProvider(this, viewModelVactory)[DetailViewModel::class.java]
        moveToDetail()
        getDetailStory()
    }

    private fun getDetailStory() {
        detailViewModel.storyDetail.observe(this) { result ->
            if (result !=null) {
                when (result) {
                    is StoriyResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is StoriyResult.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val story = result.data
                        with(binding) {
                            tvDetailName.text = story?.name
                            tvDetailDescription.text = story?.description
                            Glide.with(binding.root)
                                .load(story?.photoUrl)
                                .into(binding.ivDetailPhoto)
                        }
                    }

                    is StoriyResult.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(
                            binding.root,
                            getString(R.string.failed_load_story_detail),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun moveToDetail() {
        val storyId = intent.getStringExtra(STORY_ID)
        if (storyId != null) {
            if (detailViewModel.storyDetail.value == null) {
                detailViewModel.detailStory(storyId)
            }
        }
    }

    companion object {
        const val STORY_ID = "extra_id"
    }
}
