package com.example.cinemaapp.ViewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Repository.ListAllMoviesRepository

class ListAllMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ListAllMoviesRepository(application)

    val popularMovieList: LiveData<List<MovieModel>>
    val topRatedMovieList: LiveData<List<MovieModel>>
    val upcomingMovieList: LiveData<List<MovieModel>>

    init {
        this.popularMovieList = repository.popularMovieList
        this.topRatedMovieList = repository.topRatedMoviesList
        this.upcomingMovieList = repository.upcomingMoviesList
    }

    fun loadPopularMovieList(){
        repository.loadPopularMoviesList()
    }

    fun loadTopRatedMovieList(){
        repository.loadTopRatedMovieList()
    }

    fun loadUpcomingMovieList(){
        repository.loadUpcomingMovieList()
    }
}