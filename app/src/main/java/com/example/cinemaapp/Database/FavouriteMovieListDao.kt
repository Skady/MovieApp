package com.example.cinemaapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemaapp.Models.MovieModel

@Dao
interface FavouriteMovieListDao {
    @Query("SELECT * FROM Movies")
    fun getAll(): LiveData<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieModel)

    @Query("SELECT EXISTS (SELECT 1 FROM Movies WHERE title = :title)")
    fun exists(title: String): LiveData<Boolean>

    @Query("DELETE FROM Movies WHERE title = :title")
    fun deleteFavorite(title: String)

    @Delete
    fun deleteElement(movie: MovieModel)
}