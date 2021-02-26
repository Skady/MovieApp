package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import com.example.cinemaapp.Models.DataManager
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.Services.MoviesAPI
import com.example.cinemaapp.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavouriteMoviesActivity : AppCompatActivity() {

    var favouriteMoviesList: MutableList<MovieModel> = mutableListOf()

    private val favouriteMoviesLayoutManager by lazy {
        GridLayoutManager(this, 2)
    }

    private val favouriteMoviesRecyclerAdapter by lazy {
        MoviesRecyclerAdapter(this, DataManager.movies)
        //MoviesRecyclerAdapter(this, favouriteMoviesList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)

        getInformationFromDatabase()
    }

    private fun getInformationFromDatabase() {
        getFavouriteMoviesList()
    }

    private fun getFavouriteMoviesList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getPopularMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //TODO show error
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                for (i in response.body()?.results!!){
                    favouriteMoviesList.add(i)
                }

                val recyclerListMyFavouriteMoviesId = findViewById<RecyclerView>(R.id.recyclerListMyFavouriteMovies)
                recyclerListMyFavouriteMoviesId.layoutManager = favouriteMoviesLayoutManager
                recyclerListMyFavouriteMoviesId.adapter = favouriteMoviesRecyclerAdapter
            }

        })
    }
}