package com.afauzi.cleanarchitecturemvvm.presentation.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afauzi.cleanarchitecturemvvm.R
import com.afauzi.cleanarchitecturemvvm.data.local.AppDatabase
import com.afauzi.cleanarchitecturemvvm.data.remote.ApiProvider
import com.afauzi.cleanarchitecturemvvm.data.repository.PostRepository
import com.afauzi.cleanarchitecturemvvm.databinding.ActivityMainBinding
import com.afauzi.cleanarchitecturemvvm.presentation.adapter.PostAdapter
import com.afauzi.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel
import com.afauzi.cleanarchitecturemvvm.presentation.viewmodel.PostViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postDao = AppDatabase.getInstance(applicationContext).postDao()
        val apiService = ApiProvider.provideJsonPlaceholderApi()
        val repository = PostRepository(postDao, apiService)
        val viewModelFactory = PostViewModelFactory(repository)

        postAdapter = PostAdapter(arrayListOf())
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
        }


        viewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]

        viewModel.getCachedPosts().observe(this) { list ->
            postAdapter.setData(list)
        }
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                // Loading
                Log.d(TAG, "Loading...")
            } else {
                Log.d(TAG, "Success get data and hide loading")
            }
        }
//        viewModel.refreshPosts()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}