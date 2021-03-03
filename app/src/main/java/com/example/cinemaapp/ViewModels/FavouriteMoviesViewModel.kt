package com.example.cinemaapp.ViewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Repository.FavouriteMoviesRepository

class FavouriteMoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FavouriteMoviesRepository(application)

    val favouriteMovieList: LiveData<List<MovieModel>>

    init {
        this.favouriteMovieList = repository.favouriteMoviesList
    }

    fun loadFavouriteMovieList(){
        repository.loadFavouriteMoviesList()
    }
}