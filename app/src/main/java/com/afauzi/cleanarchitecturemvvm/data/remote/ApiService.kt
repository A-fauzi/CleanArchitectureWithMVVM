package com.afauzi.cleanarchitecturemvvm.data.remote

import com.afauzi.cleanarchitecturemvvm.domain.entities.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}