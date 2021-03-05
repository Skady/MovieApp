package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import com.example.cinemaapp.ViewModels.FavouriteMoviesViewModel
import kotlinx.android.synthetic.main.activity_favourite_movies.*

class FavouriteMoviesActivity : AppCompatActivity() {

    private lateinit var viewModel: FavouriteMoviesViewModel

    private val favouriteMoviesLayoutManager by lazy {
        GridLayoutManager(this, 2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)

        viewModel = ViewModelProvider(this).get(FavouriteMoviesViewModel::class.java)

        getInformationFromDatabase()
    }

    private fun getInformationFromDatabase() {
        viewModel.loadFavouriteMovieList()

        viewModel.favouriteMovieList.observe(this, Observer {
            recyclerListMyFavouriteMovies.adapter = MoviesRecyclerAdapter(this, it, TYPE_FAVORITE_MOVIE)
            recyclerListMyFavouriteMovies.layoutManager = favouriteMoviesLayoutManager
        })
    }
}