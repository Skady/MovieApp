package com.example.cinemaapp.Models

import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Database.AppDatabase
import com.example.cinemaapp.R

object DataManager {
    var movies: MutableList<MovieModel> = mutableListOf()

    init {
        initializeMovies()
    }

    private fun initializeMovies() {
        movies.clear()
    }

    public fun findMovie(originalTitle: String): MovieModel? {
        for(movie in movies)
            if(originalTitle == movie.original_title)
                return movie
        return null
    }
/*
    public fun addMovie(
        adult: String?,
        backdrop_path: String?,
        id: String?,
        original_language: String?,
        original_title: String?,
        overview: String?,
        popularity: String?,
        poster_path: String?,
        release_date: String?,
        title: String?,
        video: String?,
        vote_average: String?,
        vote_count: String?
    ) {
        val newMovie = MovieModel(
            adult,
            backdrop_path,
            id,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count
        )
        movies.add(newMovie)
    }
*/
    public fun removeMovie(originalTitle: String) {
        var moviesTMP: MutableList<MovieModel> = mutableListOf()

        for(movie in movies)
            if(originalTitle != movie.original_title)
                moviesTMP.add(movie)

        movies.clear()
        movies = moviesTMP
    }

}
