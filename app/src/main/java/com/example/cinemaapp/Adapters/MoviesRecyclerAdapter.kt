package com.example.cinemaapp.Adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Models.MovieInfo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cinemaapp.*
import com.squareup.picasso.Picasso

class MoviesRecyclerAdapter(private val context: Context, private val movies: MutableList<MovieInfo>) :
    RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val imageMovie = itemView.findViewById<ImageView?>(R.id.imageMovie)
        //var moviePosition = 0

        //manage click event
        init {
            itemView.setOnClickListener {
                val movie = movies[position]
                val intent = Intent(context, MovieDetailsActivity::class.java)
                //intent.putExtra(EXTRA_PRODUCT_POSITION, moviePosition)
                intent.putExtra(TITLE, movie.title)
                intent.putExtra(SUMMARY, movie?.overview)
                intent.putExtra(POSTER, movie?.poster_path)
                intent.putExtra(RATING, movie?.vote_average)
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
        holder.textTitle.text = movie?.title
        val urlImage = movies[position].poster_path
        Picasso.get().load(urlImage).into(holder.imageMovie)
    }

    override fun getItemCount() = movies.size

}