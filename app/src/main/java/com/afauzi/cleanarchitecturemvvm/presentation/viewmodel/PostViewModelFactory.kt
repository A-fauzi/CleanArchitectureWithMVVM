package com.afauzi.cleanarchitecturemvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afauzi.cleanarchitecturemvvm.data.repository.PostRepository
import com.afauzi.cleanarchitecturemvvm.domain.entities.Post
import kotlinx.coroutines.runBlocking

class PostViewModelFactory(private val postRepository: PostRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(postRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    fun getCachedPost(): LiveData<List<Post>> {
        return runBlocking { postRepository.getCachedPosts() }
    }
}