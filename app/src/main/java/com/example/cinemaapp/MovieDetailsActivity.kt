package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import com.example.cinemaapp.Database.AppDatabase
import com.example.cinemaapp.Models.DataManager
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.ViewModels.MovieDetailViewModel
import com.squareup.picasso.Picasso
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.content_main_list_all_movies.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        val favButton = findViewById<Button>(R.id.favButton)

        title = intent.getStringExtra(TITLE)

        favButton.setOnClickListener {
            val selectedMovie = getSelectedMovie()
            //viewModel.removeMovieFromFavList(selectedMovie)
            if(DataManager.findMovie(title as String) == null) {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_red_24)
                viewModel.addMovieToFavList(selectedMovie)
            }
            else {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_grey_24)
                viewModel.removeMovieFromFavList(selectedMovie)
            }
        }
        showMovie()
    }

    private fun getSelectedMovie(): MovieModel {
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
        return selectedMovie
    }

    private fun showMovie() {

        val titleElement = intent.getStringExtra(TITLE)

        titleDetailTextView.setText(titleElement)
        ratingDetailTextView.setText(intent.getStringExtra(VOTE_AVERAGE))
        summaryDetailTextView.setText(intent.getStringExtra(OVERVIEW))

        val urlImage = intent.getStringExtra(COMPLETE_POSTER_PATH)
        Picasso.get().load(urlImage).into(detailImageView)

        viewModel.getOneMovieFromFavList(titleElement as String)

        viewModel.selectedMovieIsInDB.observe(this, Observer {
            flagExist.setText("entre")
            if(it) {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_red_24)
                flagExist.setText("true")
            }
            else {
                favButton.background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_favorite_grey_24)
                flagExist.setText("false")
            }
        })
    }

}