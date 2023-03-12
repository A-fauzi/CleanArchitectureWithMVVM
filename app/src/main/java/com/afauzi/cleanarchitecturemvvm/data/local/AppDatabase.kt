package com.afauzi.cleanarchitecturemvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afauzi.cleanarchitecturemvvm.domain.entities.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { instance = it }
            }
        }
    }
}