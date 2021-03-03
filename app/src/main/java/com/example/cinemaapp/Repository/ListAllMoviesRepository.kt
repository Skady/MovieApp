package com.example.cinemaapp.Repository

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.Services.BASE_URL
import com.example.cinemaapp.Services.MoviesAPI
import com.example.cinemaapp.Services.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListAllMoviesRepository(val application: Application) {

    val popularMovieList = MutableLiveData<List<MovieModel>>()


    fun loadPopularMoviesList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getPopularMoviesList().enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(application, "Error wile accessing the API", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val a = response.body()?.results
                Log.d("TAG", a.toString() )
                popularMovieList.value = response.body()?.results
            }
        })
    }
}
    /*
    fun loadPopularMovieList() {


        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()


        val service = retrofit.create(WeatherNetwork::class.java)

        service.getLocation(searchString).enqueue(object  : Callback<List<Location>>{
            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application,"Error wile accessing the API",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                Log.d("SearchRepository" , "Response : ${Gson().toJson(response.body())}")
                locationList.value = response.body()
                showProgress.value = false
            }

        })
     */
/*
    private var popularMoviesList: MutableLiveData<MutableList<MovieModel>>
    //val serviceSetterGetter = MutableLiveData<MutableList<MovieModel>>()

    fun getPopularMoviesListAPI(): MutableList<MutableList<MovieModel>> {
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

    fun getServicesApiCall(): MutableLiveData<ServicesSetterGetter> {

        val call = RetrofitClient.apiInterface.getServices()

        call.enqueue(object: Callback<ServicesSetterGetter> {
            override fun onFailure(call: Call<ServicesSetterGetter>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<ServicesSetterGetter>,
                response: Response<ServicesSetterGetter>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                val msg = data!!.message

                serviceSetterGetter.value = ServicesSetterGetter(msg)
            }
        })

        return serviceSetterGetter
    }
*/

