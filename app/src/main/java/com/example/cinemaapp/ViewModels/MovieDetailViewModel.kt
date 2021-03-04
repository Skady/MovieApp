package com.example.cinemaapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Repository.FavouriteMoviesRepository

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FavouriteMoviesRepository(application)

    val selectedMovieIsInDB: LiveData<Boolean>

    init {
        this.selectedMovieIsInDB = repository.selectedMovieIsInDB
    }

    fun addMovieToFavList(movie: MovieModel) {
        repository.addMovie(movie)
    }

    fun removeMovieFromFavList(movie: MovieModel) {
        repository.removeMovie(movie)
    }

    fun getOneMovieFromFavList(title: String) {
        repository.getMovie(title)
    }
}