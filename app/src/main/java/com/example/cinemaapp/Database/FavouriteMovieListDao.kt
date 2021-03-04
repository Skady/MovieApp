package com.example.cinemaapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemaapp.Models.MovieModel

@Dao
interface FavouriteMovieListDao {
    @Query("SELECT * FROM favMovies")
    fun getAll(): LiveData<List<MovieModel>>

    @Query("SELECT * FROM favMovies WHERE title = :title")
    fun getOne(title: String): LiveData<MovieModel>
/*
    @Insert
    fun insertAll(vararg favMovies: MovieModel)
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: MovieModel)
/*
    @Query("DELETE FROM favMovies WHERE title = :title")
    suspend fun deleteElement(title: String): LiveData<MovieModel>
*/
    @Query("SELECT EXISTS (SELECT 1 FROM favMovies WHERE title = :title)")
    fun exists(title: String): LiveData<Boolean>

    @Delete
    fun delete(movie: MovieModel)
}