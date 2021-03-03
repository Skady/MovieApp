package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.example.cinemaapp.Database.AppDatabase
import com.example.cinemaapp.Models.DataManager
import com.example.cinemaapp.Models.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val favButton = findViewById<Button>(R.id.favButton)

        title = intent.getStringExtra(TITLE)
        favButton.setOnClickListener {
            if(DataManager.findMovie(title as String) == null) {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_red_24)
                addMovieToFavList()
                addToDataManager()
            }
            else {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_grey_24)
                DataManager.removeMovie(title as String)
                //deleteMovieToFavList()
            }
        }

        showMovie()
    }

    private fun deleteMovieToFavList() {
        val database = AppDatabase.getDatabase(this)

        val selectedMovie = MovieModel(
            intent.getStringExtra(ADULT),
            intent.getStringExtra(BACKDROP_PATH),
            intent.getStringExtra(ID),
            intent.getStringExtra(ORIGINAL_LANGUAGE),
            intent.getStringExtra(ORIGINAL_TITLE),
            intent.getStringExtra(OVERVIEW),
            intent.getStringExtra(POPULARITY),
            intent.getStringExtra(POSTER_PATH),
            intent.getStringExtra(RELEASE_DATE),
            intent.getStringExtra(TITLE),
            intent.getStringExtra(VIDEO),
            intent.getStringExtra(VOTE_AVERAGE),
            intent.getStringExtra(VOTE_COUNT)
        )

        database.favMoviesDao().delete(selectedMovie);
    }

    private fun addMovieToFavList() {
        val database = AppDatabase.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            val newMovie = MovieModel(
                intent.getStringExtra(ADULT),
                intent.getStringExtra(BACKDROP_PATH),
                intent.getStringExtra(ID),
                intent.getStringExtra(ORIGINAL_LANGUAGE),
                intent.getStringExtra(ORIGINAL_TITLE),
                intent.getStringExtra(OVERVIEW),
                intent.getStringExtra(POPULARITY),
                intent.getStringExtra(POSTER_PATH),
                intent.getStringExtra(RELEASE_DATE),
                intent.getStringExtra(TITLE),
                intent.getStringExtra(VIDEO),
                intent.getStringExtra(VOTE_AVERAGE),
                intent.getStringExtra(VOTE_COUNT)
            )
            database.favMoviesDao().insertAll(newMovie)
        }
    }

    private fun addToDataManager() {
        DataManager.addMovie(
            intent.getStringExtra(ADULT),
            intent.getStringExtra(BACKDROP_PATH),
            intent.getStringExtra(ID),
            intent.getStringExtra(ORIGINAL_LANGUAGE),
            intent.getStringExtra(ORIGINAL_TITLE),
            intent.getStringExtra(OVERVIEW),
            intent.getStringExtra(POPULARITY),
            intent.getStringExtra(POSTER_PATH),
            intent.getStringExtra(RELEASE_DATE),
            intent.getStringExtra(TITLE),
            intent.getStringExtra(VIDEO),
            intent.getStringExtra(VOTE_AVERAGE),
            intent.getStringExtra(VOTE_COUNT)
        )
    }
    private fun showMovie() {

        val titleId = findViewById<TextView>(R.id.titleDetailTextView)
        titleId.setText(intent.getStringExtra(TITLE))

        val ratingId = findViewById<TextView>(R.id.ratingDetailTextView)
        ratingId.setText(intent.getStringExtra(VOTE_AVERAGE))

        val summaryId = findViewById<TextView>(R.id.summaryDetailTextView)
        summaryId.setText(intent.getStringExtra(OVERVIEW))

        val imageDetailMovieID = findViewById<ImageView?>(R.id.detailImageView)
        val urlImage = intent.getStringExtra(COMPLETE_POSTER_PATH)
        Picasso.get().load(urlImage).into(imageDetailMovieID)

        val titleElement = intent.getStringExtra(TITLE)

        val favButton = findViewById<Button>(R.id.favButton)
        if(DataManager.findMovie(titleElement as String) != null)
            favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_red_24)
        else
            favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_grey_24)
    }

}