package com.example.cinemaapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cinemaapp.Models.MovieModel

@Database(entities = [MovieModel::class], version = 3)
abstract class AllMoviesDatabase : RoomDatabase() {

    abstract fun allMoviesDao(): AllMoviesListDao

    companion object {
        @Volatile
        private var INSTANCE: AllMoviesDatabase? = null

        fun getDatabase(context: Context): AllMoviesDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AllMoviesDatabase::class.java,
                    "all_movies_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance

                return instance
            }
        }
    }
}