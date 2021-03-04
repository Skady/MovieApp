package com.example.cinemaapp.Repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.Models.VideoResponse
import com.example.cinemaapp.Services.MoviesAPI
import com.example.cinemaapp.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListAllMoviesRepository(val application: Application) {

    val popularMovieList = MutableLiveData<List<MovieModel>>()
    val topRatedMoviesList = MutableLiveData<List<MovieModel>>()
    val upcomingMoviesList = MutableLiveData<List<MovieModel>>()

    val selectedMovieTrailerID = MutableLiveData<String>()

    fun loadPopularMoviesList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getPopularMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(application, "Error while loading popular movie list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                popularMovieList.value = response.body()?.results
            }
        })
    }

    fun loadTopRatedMovieList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getTopRatedMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(application, "Error while accessing top rated movie list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                topRatedMoviesList.value = response.body()?.results
            }
        })
    }

    fun loadUpcomingMovieList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getUpcomingRatedMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(application, "Error while accessing upcoming movie list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                upcomingMoviesList.value = response.body()?.results
            }
        })
    }

    fun getTrailerKey(movieID: String) {
        RetrofitClient.buildService(MoviesAPI::class.java).getVideosList(movieID).enqueue(object :
            Callback<VideoResponse> {
            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                Toast.makeText(application, "Error while getting video list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                selectedMovieTrailerID.value = response.body()?.results?.get(0)?.key
            }
        })
    }
}