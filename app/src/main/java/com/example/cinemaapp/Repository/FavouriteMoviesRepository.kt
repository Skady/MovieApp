package com.example.cinemaapp.Repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.Database.FavouriteMoviesDatabase
import com.example.cinemaapp.Models.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteMoviesRepository(val application: Application) {

    val favouriteMoviesList = MutableLiveData<List<MovieModel>>()
    val selectedMovieIsInDB = MutableLiveData<Boolean>()

    private val databaseFavouriteMovies = FavouriteMoviesDatabase.getDatabase(application)

    fun loadFavouriteMoviesList() {
        databaseFavouriteMovies.favMoviesDao().getAll().observeForever {
            favouriteMoviesList.value = it
        }
    }

    fun addMovie(movie: MovieModel) {
        CoroutineScope(Dispatchers.IO).launch {
            databaseFavouriteMovies.favMoviesDao().insert(movie)
        }
    }

    fun removeMovie(movie: MovieModel) {
        CoroutineScope(Dispatchers.IO).launch {
            databaseFavouriteMovies.favMoviesDao().deleteElement(movie)
        }
    }

    fun getMovie(title: String) {
        databaseFavouriteMovies.favMoviesDao().exists(title).observeForever {
            selectedMovieIsInDB.value = it
        }
    }
}