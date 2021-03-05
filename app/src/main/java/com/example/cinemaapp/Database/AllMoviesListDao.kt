package com.example.cinemaapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemaapp.Models.MovieModel

@Dao
interface AllMoviesListDao {
    @Query("SELECT * FROM Movies WHERE type = :type")
    fun getAllMovieWithTypeList(type: String): LiveData<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll( videos: List<MovieModel>)
}