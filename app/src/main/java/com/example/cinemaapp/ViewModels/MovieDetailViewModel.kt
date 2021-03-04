package com.example.cinemaapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Repository.FavouriteMoviesRepository
import com.example.cinemaapp.Repository.ListAllMoviesRepository

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FavouriteMoviesRepository(application)
    private val repositoryAllMovies = ListAllMoviesRepository(application)

    val selectedMovieIsInDB: LiveData<Boolean>
    val selectedMovieTrailerID: LiveData<String>

    init {
        this.selectedMovieIsInDB = repository.selectedMovieIsInDB
        this.selectedMovieTrailerID = repositoryAllMovies.selectedMovieTrailerID
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

    fun getTrailerKey(movieID: String) {
        repositoryAllMovies.getTrailerKey(movieID)
    }
}