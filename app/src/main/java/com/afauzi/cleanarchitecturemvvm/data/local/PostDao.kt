package com.afauzi.cleanarchitecturemvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afauzi.cleanarchitecturemvvm.domain.entities.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Post>)
}