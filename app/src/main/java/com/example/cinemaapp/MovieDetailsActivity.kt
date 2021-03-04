package com.example.cinemaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.ViewModels.MovieDetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        var isInDB: Boolean = false
        title = intent.getStringExtra(TITLE)

        viewModel.getOneMovieFromFavList(title as String)
        viewModel.selectedMovieIsInDB.observe(this, Observer {
            isInDB = it
        })

        favButton.setOnClickListener {
            val selectedMovie = getSelectedMovie()
            if(isInDB) {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_grey_24)
                viewModel.removeMovieFromFavList(selectedMovie)
                flagExist.setText("true from click")
            }
            else {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_red_24)
                viewModel.addMovieToFavList(selectedMovie)
                flagExist.setText("false from click")
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
        titleDetailTextView.setText(intent.getStringExtra(TITLE))
        ratingDetailTextView.setText(intent.getStringExtra(VOTE_AVERAGE))
        summaryDetailTextView.setText(intent.getStringExtra(OVERVIEW))

        val urlImage = intent.getStringExtra(COMPLETE_POSTER_PATH)
        Picasso.get().load(urlImage).into(detailImageView)

        viewModel.getOneMovieFromFavList(title as String)
        viewModel.selectedMovieIsInDB.observe(this, Observer {
            if(it) {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_red_24)
                flagExist.setText("true")
            }
            else {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_grey_24)
                flagExist.setText("false")
            }
        })

        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "kP9TfCWaQT4"
                youTubePlayer.loadVideo("kP9TfCWaQT4", 0f)
            }
        })
    }
}