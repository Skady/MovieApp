package com.example.cinemaapp

import android.app.Application
import com.example.cinemaapp.Database.AppDatabase
import com.example.cinemaapp.Repository.FavouriteMoviesRepository

class MoviesApplication : Application() {
    /*val database by lazy { AppDatabase.getDatabase(this) }
    val repositoryFavourite by lazy { FavouriteMoviesRepository(database.favMoviesDao()) }*/
}