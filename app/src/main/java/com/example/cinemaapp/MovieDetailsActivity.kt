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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        lifecycle.addObserver(youtube_player_view);

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
            }
            else {
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_red_24)
                viewModel.addMovieToFavList(selectedMovie)
            }
        }
        showMovie()
    }

    private fun getSelectedMovie(): MovieModel {
        val id: String = intent.getStringExtra(ID) as String;

        val selectedMovie = MovieModel(
            intent.getStringExtra(ADULT),
            intent.getStringExtra(BACKDROP_PATH),
            id,
            intent.getStringExtra(ORIGINAL_LANGUAGE),
            intent.getStringExtra(ORIGINAL_TITLE),
            intent.getStringExtra(OVERVIEW),
            intent.getStringExtra(POPULARITY),
            intent.getStringExtra(POSTER_PATH),
            intent.getStringExtra(RELEASE_DATE),
            intent.getStringExtra(TITLE),
            intent.getStringExtra(VIDEO),
            intent.getStringExtra(VOTE_AVERAGE),
            intent.getStringExtra(VOTE_COUNT),
            TYPE_FAVORITE_MOVIE
        )
        return selectedMovie
    }

    private fun showMovie() {
        titleDetailTextView.setText(intent.getStringExtra(TITLE))
        summaryDetailTextView.setText(intent.getStringExtra(OVERVIEW))

        val urlImage = intent.getStringExtra(COMPLETE_POSTER_PATH)
        Picasso.get().load(urlImage).into(detailImageView)

        viewModel.getOneMovieFromFavList(title as String)
        viewModel.selectedMovieIsInDB.observe(this, Observer {
            if(it)
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_red_24)
            else
                favButton.background = AppCompatResources.getDrawable(this, R.drawable.ic_favorite_grey_24)
        })

        //for trailer
        val movieID = intent.getStringExtra(ID)

        viewModel.getTrailerKey(movieID as String)
        viewModel.selectedMovieTrailerID.observe(this, Observer {
            youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = it
                    youTubePlayer.loadVideo(videoId, 0f)
                    youTubePlayer.pause()
                }
            })
        })
        //TODO get movie detail from IMDB
        //for movie detail
        val imdbID = "tt3896198"

        viewModel.getMovieDetailOMDB(imdbID as String)
        viewModel.selectedMovieDetail.observe(this, Observer {
            imdbRatingDetailTextView.setText(it.imdbRating)
            directorDetailTextView.setText(it.Director)
            castDetailTextView.setText(it.Actors)
        })
    }
}