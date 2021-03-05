package com.example.cinemaapp.Repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.Database.AllMoviesDatabase
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.Models.VideoResponse
import com.example.cinemaapp.Services.MoviesAPI
import com.example.cinemaapp.Services.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListAllMoviesRepository(val application: Application) {

    val popularMovieList = MutableLiveData<List<MovieModel>>()
    val topRatedMoviesList = MutableLiveData<List<MovieModel>>()
    val upcomingMoviesList = MutableLiveData<List<MovieModel>>()

    val selectedMovieTrailerID = MutableLiveData<String>()

    val database = AllMoviesDatabase.getDatabase(application)

    private fun mapToAddType(movieList: List<MovieModel>, type: String) : List<MovieModel> {
        return movieList.map {
            MovieModel (
                it.adult,
                it.backdrop_path,
                it.id,
                it.original_language,
                it.original_title,
                it.overview,
                it.popularity,
                it.poster_path,
                it.release_date,
                it.title,
                it.video,
                it.vote_average,
                it.vote_count,
                type
            )
        }
    }

    fun loadPopularMoviesList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getPopularMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                database.allMoviesDao().getAllMovieWithTypeList("POPULAR_MOVIE").observeForever {
                    popularMovieList.value = it
                }
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                popularMovieList.value = response.body()?.results

                val movieList: List<MovieModel> = mapToAddType(popularMovieList.value as List<MovieModel>, "POPULAR_MOVIE")
                CoroutineScope(Dispatchers.IO).launch {
                    database.allMoviesDao().insertAll(movieList)
                }
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
                if(response.body()?.results?.size == 0)
                    selectedMovieTrailerID.value = "05DqIGS_koU"
                else
                    selectedMovieTrailerID.value = response.body()?.results?.get(0)?.key
            }
        })
    }
}