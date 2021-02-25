package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Models.DataManager
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class MovieDetailsActivity : AppCompatActivity() {

    private var productPosition = 0
    private val picasso = Picasso.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        showMovie()
    }

    private fun showMovie() {

        val product = DataManager.movies[productPosition]
        /*
        editTextName.setText(product.name)
        editTextType.setText(product.type)
        editTextDescription.setText(product.description)*/

        val titleId = findViewById<TextView>(R.id.titleDetailTextView)
        titleId.setText(intent.getStringExtra(TITLE))

        val ratingId = findViewById<TextView>(R.id.ratingDetailTextView)
        ratingId.setText(intent.getStringExtra(RATING))

        val summaryId = findViewById<TextView>(R.id.summaryDetailTextView)
        summaryId.setText(intent.getStringExtra(SUMMARY))

        //val titleDetailTextView = intent.getStringExtra(TITLE)
        /*
        summaryDetailTextView.text = intent.getStringExtra(SUMMARY)
        ratingDetailTextView.text = intent.getStringExtra(SUMMARY)

        titleDetailTextView.setText()*/

        //ratingTextView.text = String.format(getString(R.string.rating),intent.getFloatExtra(RATING, 1f).roundToInt())
        /*picasso
            .load(RetrofitClient.TMDB_IMAGEURL + intent.getStringExtra(POSTER))
            .error(R.drawable.iconfinder_movie_285656)
            .into(detailImageView)

         */
    }

}