package com.example.cinemaapp.Services

import com.example.cinemaapp.Models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    @GET("movie/popular?api_key=dc727792d53fac39b6e09ce16833db99&language=en-US")
    fun getPopularMoviesList(): Call<MovieResponse>

}