package com.afauzi.cleanarchitecturemvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afauzi.cleanarchitecturemvvm.data.repository.PostRepository
import com.afauzi.cleanarchitecturemvvm.domain.entities.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class PostViewModel(private val postRepository: PostRepository): ViewModel() {
//    private val _posts = MutableLiveData<List<Post>>()
//    val posts: LiveData<List<Post>>
//        get() = _posts
//
//    fun getPosts() {
//        viewModelScope.launch {
//            try {
//                _posts.value = postRepository.getPosts()
//            }catch (e: Exception) {
//                Log.e(TAG, "Error retrieving popular movies", e)
//            }
//        }
//    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        refreshPosts()
    }

    fun refreshPosts() {
        _isLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                postRepository.refreshPost()
            }
            _isLoading.value = false
        }
    }

    fun getCachedPosts(): LiveData<List<Post>> {
        return runBlocking { postRepository.getCachedPosts() }
    }

    companion object {
        private const val TAG = "PostViewModel"
    }
}