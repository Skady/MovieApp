package com.example.cinemaapp.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favMovies")
class MovieModel(
    val adult: String?,
    val backdrop_path: String?,
    //val genre_ids: List<Int>,
    val id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: String?,
    val vote_average: String?,
    val vote_count: String?,

    @PrimaryKey(autoGenerate = true)
    var idMovie: Int = 0

) : Serializable