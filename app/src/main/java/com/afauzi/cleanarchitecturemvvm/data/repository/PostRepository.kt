package com.afauzi.cleanarchitecturemvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.afauzi.cleanarchitecturemvvm.data.local.PostDao
import com.afauzi.cleanarchitecturemvvm.data.remote.ApiService
import com.afauzi.cleanarchitecturemvvm.domain.entities.Post

class PostRepository(private val postDao: PostDao, private val apiService: ApiService) {
//    private val apiService: ApiService = ApiProvider.provideJsonPlaceholderApi()
//
//    suspend fun getPosts(): List<Post> {
//        val response = apiService.getPosts()
//        if (response.isSuccessful) {
//            return response.body() ?: emptyList()
//        } else {
//            throw Exception("Error retrieving posts")
//        }
//    }

    suspend fun refreshPost() {
        try {
            val response = apiService.getPosts()
            if (response.isSuccessful) {
                val movies = response.body()
                if (!movies.isNullOrEmpty()) {
                    postDao.insertPosts(response.body()!!)
                }
            }
        }catch (e: Exception) {
            Log.e("PostRepository", "Exception refreshing posts", e)
        }
    }

    fun getCachedPosts(): LiveData<List<Post>> {
        return postDao.getPosts()
    }

}