package com.example.cinemaapp.Adapters

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemaapp.*
import com.example.cinemaapp.Models.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MoviesRecyclerAdapter(private val context: Context, private val movies: List<MovieModel> = ArrayList()) :
    RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = movies.size

    private val layoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle = itemView.textViewTitle!!
        val imageMovie = itemView.imageMovie!!

        //manage click event
        init {
            itemView.setOnClickListener {
                val movie = movies[position]
                val intent = Intent(context, MovieDetailsActivity::class.java)

                intent.putExtra(ADULT, movie.adult)
                intent.putExtra(BACKDROP_PATH, movie.backdrop_path)
                intent.putExtra(ID, movie.id)
                intent.putExtra(ORIGINAL_LANGUAGE, movie.original_language)
                intent.putExtra(ORIGINAL_TITLE, movie.original_title)
                intent.putExtra(OVERVIEW, movie.overview)
                intent.putExtra(POPULARITY, movie.popularity)
                intent.putExtra(POSTER_PATH, movie.poster_path)

                val urlDetailImage = "https://image.tmdb.org/t/p/original" + movie.poster_path
                intent.putExtra(COMPLETE_POSTER_PATH, urlDetailImage)

                intent.putExtra(RELEASE_DATE, movie.release_date)
                intent.putExtra(TITLE, movie.title)
                intent.putExtra(VIDEO, movie.video)
                intent.putExtra(VOTE_AVERAGE, movie.vote_average)
                intent.putExtra(VOTE_COUNT, movie.vote_count)

                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_movie_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.textTitle.text = movie.title
        val urlImage = "https://image.tmdb.org/t/p/original" + movies[position].poster_path
        Picasso.get().load(urlImage).into(holder.imageMovie)
    }
}