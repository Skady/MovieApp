package com.example.cinemaapp.Repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.Database.AllMoviesDatabase
import com.example.cinemaapp.Models.MovieDetail
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.Models.VideoResponse
import com.example.cinemaapp.Services.MoviesAPI
import com.example.cinemaapp.Services.RetrofitClient
import com.example.cinemaapp.TYPE_POPULAR_MOVIE
import com.example.cinemaapp.TYPE_TOP_RATED_MOVIE
import com.example.cinemaapp.TYPE_UPCOMING_MOVIE
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

    val selectedMovieDetail = MutableLiveData<MovieDetail>()

    val selectedMovieTrailerID = MutableLiveData<String>()

    private val databaseAllMovies = AllMoviesDatabase.getDatabase(application)

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
        RetrofitClient.buildServiceIMDB(MoviesAPI::class.java).getPopularMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                databaseAllMovies.allMoviesDao().getAllMovieWithTypeList(TYPE_POPULAR_MOVIE).observeForever {
                    popularMovieList.value = it
                }
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                popularMovieList.value = response.body()?.results

                val movieList: List<MovieModel> = mapToAddType(popularMovieList.value as List<MovieModel>, TYPE_POPULAR_MOVIE)
                CoroutineScope(Dispatchers.IO).launch {
                    databaseAllMovies.allMoviesDao().insertAll(movieList)
                }
            }
        })
    }

    fun loadTopRatedMovieList() {
        RetrofitClient.buildServiceIMDB(MoviesAPI::class.java).getTopRatedMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                databaseAllMovies.allMoviesDao().getAllMovieWithTypeList(TYPE_TOP_RATED_MOVIE).observeForever {
                    topRatedMoviesList.value = it
                }
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                topRatedMoviesList.value = response.body()?.results

                val movieList: List<MovieModel> = mapToAddType(topRatedMoviesList.value as List<MovieModel>, TYPE_TOP_RATED_MOVIE)
                CoroutineScope(Dispatchers.IO).launch {
                    databaseAllMovies.allMoviesDao().insertAll(movieList)
                }
            }
        })
    }

    fun loadUpcomingMovieList() {
        RetrofitClient.buildServiceIMDB(MoviesAPI::class.java).getUpcomingRatedMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                databaseAllMovies.allMoviesDao().getAllMovieWithTypeList(TYPE_UPCOMING_MOVIE).observeForever {
                    upcomingMoviesList.value = it
                }
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                upcomingMoviesList.value = response.body()?.results

                val movieList: List<MovieModel> = mapToAddType(upcomingMoviesList.value as List<MovieModel>, TYPE_UPCOMING_MOVIE)
                CoroutineScope(Dispatchers.IO).launch {
                    databaseAllMovies.allMoviesDao().insertAll(movieList)
                }
            }
        })
    }

    fun getTrailerKey(movieID: String) {
        RetrofitClient.buildServiceIMDB(MoviesAPI::class.java).getVideosList(movieID).enqueue(object :
            Callback<VideoResponse> {
            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                Toast.makeText(application, "No network, trailer could not be load", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                if(response.body()?.results?.size == 0)
                    selectedMovieTrailerID.value = "05DqIGS_koU"
                else
                    selectedMovieTrailerID.value = response.body()?.results?.get(0)?.key
            }
        })
    }

    fun getMovieDetailOMDB(imdbID: String) {
        RetrofitClient.buildServiceOMDB(MoviesAPI::class.java).getMovieCompleteDetail(imdbID, "41bd1bcf").enqueue(object :
            Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Toast.makeText(application, "Movie detail could not be found", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                selectedMovieDetail.value = response.body()
            }
        })
    }
}