package com.example.cinemaapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemaapp.Models.MovieModel

@Dao
interface FavouriteMovieListDao {
    @Query("SELECT * FROM favMovies")
    fun getAll(): LiveData<List<MovieModel>>

    @Query("SELECT * FROM favMovies WHERE title = :title")
    fun get(title: String): LiveData<MovieModel>

    @Insert
    fun insertAll(vararg favMovies: MovieModel)

    /*
    @Query("DELETE FROM favMovies WHERE title = :title")
    fun deleteElement(title: String): LiveData<MovieModel>
*/

    @Delete
    fun delete(movie: MovieModel)
}