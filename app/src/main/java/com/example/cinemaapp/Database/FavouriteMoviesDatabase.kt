package com.example.cinemaapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cinemaapp.Models.MovieModel

@Database(entities = [MovieModel::class], version = 3)
abstract class FavouriteMoviesDatabase : RoomDatabase() {

    abstract fun favMoviesDao(): FavouriteMovieListDao

    companion object {
        @Volatile
        private var INSTANCE: FavouriteMoviesDatabase? = null

        fun getDatabase(context: Context): FavouriteMoviesDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteMoviesDatabase::class.java,
                    "favourite_movies_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance

                return instance
            }
        }
    }
}
