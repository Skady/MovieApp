package com.example.cinemaapp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("results")
    @Expose
    var results: List<MovieModel>? = null
}