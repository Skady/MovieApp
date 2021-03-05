package com.example.cinemaapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Repository.ListAllMoviesRepository

class SearchMovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ListAllMoviesRepository(application)

    val searchResponseMoviesList: LiveData<List<MovieModel>>

    init {
        this.searchResponseMoviesList = repository.searchResponseMoviesList
    }

    fun loadSearchResponseMoviesList(title: String){
        repository.searchMovie(title)
    }
}