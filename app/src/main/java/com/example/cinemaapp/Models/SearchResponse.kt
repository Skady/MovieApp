package com.example.cinemaapp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("Search")
    @Expose
    var results: List<MovieModel>? = null
}