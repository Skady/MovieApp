package com.example.cinemaapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemaapp.Models.MovieModel

@Dao
interface FavouriteMovieListDao {
    @Query("SELECT * FROM favMovies WHERE type = 'FAVOURITE_MOVIE'")
    fun getAll(): LiveData<List<MovieModel>>

    @Query("SELECT * FROM favMovies WHERE title = :title")
    fun getOne(title: String): LiveData<MovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieModel)

    @Query("SELECT EXISTS (SELECT 1 FROM favMovies WHERE title = :title)")
    fun exists(title: String): LiveData<Boolean>

    @Query("DELETE FROM favMovies WHERE title = :title")
    fun deleteByTitle(title: String)

    @Delete
    fun deleteElement(movie: MovieModel)
}