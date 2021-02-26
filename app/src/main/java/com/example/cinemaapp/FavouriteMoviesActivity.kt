package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import com.example.cinemaapp.Database.AppDatabase
import com.example.cinemaapp.Models.DataManager
import com.example.cinemaapp.Models.MovieModel

class FavouriteMoviesActivity : AppCompatActivity() {

    var favouriteMoviesList: MutableList<MovieModel> = mutableListOf()

    private val favouriteMoviesLayoutManager by lazy {
        GridLayoutManager(this, 2)
    }

    private val favouriteMoviesRecyclerAdapter by lazy {
        MoviesRecyclerAdapter(this, favouriteMoviesList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)

        getInformationFromDatabase()
    }

    private fun getInformationFromDatabase() {
        val database = AppDatabase.getDatabase(this)
        database.favMovies().getAll().observe(this, Observer {
            favouriteMoviesList.clear()
            for(movie in it)
                if(DataManager.findMovie(movie.title as String) != null)
                    favouriteMoviesList.add(movie)

            val recyclerListMyFavouriteMoviesId = findViewById<RecyclerView>(R.id.recyclerListMyFavouriteMovies)
            recyclerListMyFavouriteMoviesId.layoutManager = favouriteMoviesLayoutManager
            recyclerListMyFavouriteMoviesId.adapter = favouriteMoviesRecyclerAdapter
        })
    }
}