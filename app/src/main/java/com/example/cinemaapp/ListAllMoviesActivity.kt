package com.example.cinemaapp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import com.example.cinemaapp.Models.DataManager
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.Services.MoviesAPI
import com.example.cinemaapp.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListAllMoviesActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    var popularMoviesList: MutableList<MovieModel> = mutableListOf()

    private val moviesLayoutManager by lazy {
       //GridLayoutManager(this, 2)
       LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    private val moviesRecyclerAdapter by lazy {
        MoviesRecyclerAdapter(this, popularMoviesList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_all_movies)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        //val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        val toggle = ActionBarDrawerToggle (this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        getPopularMoviesList()
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
        /*val recyclerListMoviesId = findViewById<RecyclerView>(R.id.recyclerListMovies)
        recyclerListMoviesId.layoutManager = moviesLayoutManager
        recyclerListMoviesId.adapter = moviesRecyclerAdapter*/
    }

    private fun getPopularMoviesList() {
        var popularMoviesService = RetrofitClient.buildService(MoviesAPI::class.java)
        var call = popularMoviesService.getPopularMoviesList()

        RetrofitClient.buildService(MoviesAPI::class.java).getPopularMoviesList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //TODO show error
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                for (i in response.body()?.results!!){
                    popularMoviesList.add(i)
                }

                val recyclerListMoviesId = findViewById<RecyclerView>(R.id.recyclerListMovies)
                recyclerListMoviesId.layoutManager = moviesLayoutManager
                recyclerListMoviesId.adapter = moviesRecyclerAdapter
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.list_all_movies, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}