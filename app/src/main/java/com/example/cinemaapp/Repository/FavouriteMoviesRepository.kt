package com.example.cinemaapp.Repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.Database.AppDatabase
import com.example.cinemaapp.Models.MovieModel
import androidx.lifecycle.Observer

class FavouriteMoviesRepository(val application: Application) {

    val favouriteMoviesList = MutableLiveData<List<MovieModel>>()

    fun loadFavouriteMoviesList() {
        val database = AppDatabase.getDatabase(application)
        database.favMoviesDao().getAll().observeForever {
            favouriteMoviesList.value = it
        }
    }
}