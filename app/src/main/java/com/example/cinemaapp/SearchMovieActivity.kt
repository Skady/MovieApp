package com.example.cinemaapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import com.example.cinemaapp.ViewModels.SearchMovieViewModel
import kotlinx.android.synthetic.main.activity_search_movie.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

class SearchMovieActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchMovieViewModel
    lateinit var context: Context

    private val searchMoviesLayoutManager by lazy {
        GridLayoutManager(this, 2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        context = this@SearchMovieActivity

        viewModel = ViewModelProvider(this).get(SearchMovieViewModel::class.java)

        viewModel.loadSearchResponseMoviesList("")

        buttonSearchMovie.setOnClickListener {
            val title = titleToSearchTextEdit.text
            viewModel.loadSearchResponseMoviesList(title as String)

            viewModel.searchResponseMoviesList.observe(this, Observer {
                recyclerSearchResultMovies.adapter = MoviesRecyclerAdapter(this, it, "TYPE SEARCH")
                recyclerSearchResultMovies.layoutManager = searchMoviesLayoutManager
            })
        }
    }
}