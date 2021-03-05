package com.example.cinemaapp.Services

import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.Models.SearchResponse
import com.example.cinemaapp.Models.VideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {
    @GET("movie/popular?api_key=dc727792d53fac39b6e09ce16833db99&language=en-US")
    fun getPopularMoviesList(): Call<MovieResponse>

    @GET("movie/top_rated?api_key=dc727792d53fac39b6e09ce16833db99&language=en-US")
    fun getTopRatedMoviesList(): Call<MovieResponse>

    @GET("movie/upcoming?api_key=dc727792d53fac39b6e09ce16833db99&language=en-US")
    fun getUpcomingRatedMoviesList(): Call<MovieResponse>

    @GET("movie/{movieID}/videos?api_key=dc727792d53fac39b6e09ce16833db99&language=en-US")
    fun getVideosList(@Path("movieID") movieID: String): Call<VideoResponse>

    @GET("movie/{movieID}?api_key=dc727792d53fac39b6e09ce16833db99&language=en-US")
    fun geMovieDetailFromIMDB(@Path("movieID") movieID: String): Call<MovieModel>

    @GET("/")
    fun getMovieDetailFromOMDB(@Query("i") i: String, @Query("apikey") apikey: String): Call<MovieModel>

    @GET("/")
    fun searchMovieFromOMDB(@Query("s") s: String, @Query("apikey") apikey: String): Call<SearchResponse>
}