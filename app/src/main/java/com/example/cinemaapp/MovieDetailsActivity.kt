package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Models.DataManager
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        showMovie()
    }

    private fun showMovie() {

        val titleId = findViewById<TextView>(R.id.titleDetailTextView)
        titleId.setText(intent.getStringExtra(TITLE))

        val ratingId = findViewById<TextView>(R.id.ratingDetailTextView)
        ratingId.setText(intent.getStringExtra(RATING))

        val summaryId = findViewById<TextView>(R.id.summaryDetailTextView)
        summaryId.setText(intent.getStringExtra(SUMMARY))

        val imageDetailMovieID = findViewById<ImageView?>(R.id.detailImageView)
        val urlImage = intent.getStringExtra(POSTER)
        Picasso.get().load(urlImage).into(imageDetailMovieID)
        //ratingTextView.text = String.format(getString(R.string.rating),intent.getFloatExtra(RATING, 1f).roundToInt())
        /*picasso
            .load(RetrofitClient.TMDB_IMAGEURL + intent.getStringExtra(POSTER))
            .error(R.drawable.iconfinder_movie_285656)
            .into(detailImageView)

         */
    }

}