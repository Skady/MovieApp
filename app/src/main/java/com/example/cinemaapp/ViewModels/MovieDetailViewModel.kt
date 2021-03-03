package com.example.cinemaapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Repository.FavouriteMoviesRepository

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FavouriteMoviesRepository(application)

    fun addMovieToFavList(movie: MovieModel) {
        repository.addMovie(movie)
    }
}