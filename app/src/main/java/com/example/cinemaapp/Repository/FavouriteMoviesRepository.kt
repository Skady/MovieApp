package com.example.cinemaapp.Repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.Database.AppDatabase
import com.example.cinemaapp.Models.MovieModel
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteMoviesRepository(val application: Application) {

    val favouriteMoviesList = MutableLiveData<List<MovieModel>>()
    val database = AppDatabase.getDatabase(application)

    fun loadFavouriteMoviesList() {
        database.favMoviesDao().getAll().observeForever {
            favouriteMoviesList.value = it
        }
    }

    fun addMovie(movie: MovieModel) {
        CoroutineScope(Dispatchers.IO).launch {
            database.favMoviesDao().insertAll(movie)
        }
    }
}