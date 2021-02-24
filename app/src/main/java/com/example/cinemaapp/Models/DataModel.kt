package com.example.cinemaapp.Models

data class MovieInfo (
        var title: String,
        var poster_path: String? = null,
        var overview: String? = null,
        var vote_average: String? = null) {

    override fun toString(): String {
        return title
    }
}
