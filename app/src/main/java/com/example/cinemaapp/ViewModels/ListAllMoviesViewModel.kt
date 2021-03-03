package com.example.cinemaapp.ViewModels

import android.app.Application
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.R
import com.example.cinemaapp.Repository.ListAllMoviesRepository
//import com.example.cinemaapp.Repository.AllMoviesAPIRepository
import com.example.cinemaapp.Services.MoviesAPI
import com.example.cinemaapp.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListAllMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ListAllMoviesRepository(application)

    val popularMovieList: LiveData<List<MovieModel>>

    init {
        this.popularMovieList = repository.popularMovieList
    }

    fun loadPopularMovieList(){
        repository.loadPopularMoviesList()
    }
}
/*
    private var popularMoviesList: MutableList<MovieModel> = mutableListOf()
    private var incomingMoviesList: MutableList<MovieModel> = mutableListOf()

    //var servicesLiveData: MutableLiveData<ServicesSetterGetter>? = null

    fun getPopularListRepo() : LiveData<MutableList<MovieModel>>? {
        popularMoviesList = MainActivityRepository.getServicesApiCall()
        return popularMoviesList
    }

    fun getPopularMoviesList(): MutableList<MovieModel> {
        RetrofitClient.buildService(MoviesAPI::class.java).getPopularMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //TODO show error
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                for (i in response.body()?.results!!){
                    popularMoviesList.add(i)
                }
            }
        })

        return popularMoviesList
    }

    fun getTopRatedMoviesList() : MutableList<MovieModel> {
        var topRatedMoviesList: MutableList<MovieModel> = mutableListOf()

        RetrofitClient.buildService(MoviesAPI::class.java).getTopRatedMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //TODO show error
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                for (i in response.body()?.results!!){
                    topRatedMoviesList.add(i)
                }
            }
        })

        return topRatedMoviesList
    }

    fun getIncomingMoviesList() : MutableList<MovieModel> {

        RetrofitClient.buildService(MoviesAPI::class.java).getIncomingRatedMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //TODO show error
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                for (i in response.body()?.results!!){
                    incomingMoviesList.add(i)
                }
            }

        })

        return incomingMoviesList
    }
}
 */
/*
class ListAllMoviesViewModelFactory(private val repository: AllMoviesAPIRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListAllMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListAllMoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/