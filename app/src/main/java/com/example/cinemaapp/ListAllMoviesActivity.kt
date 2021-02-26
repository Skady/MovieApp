package com.example.cinemaapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import com.example.cinemaapp.Models.MovieModel
import com.example.cinemaapp.Models.MovieResponse
import com.example.cinemaapp.Services.MoviesAPI
import com.example.cinemaapp.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListAllMoviesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    var popularMoviesList: MutableList<MovieModel> = mutableListOf()
    var topRatedMoviesList: MutableList<MovieModel> = mutableListOf()
    var incomingMoviesList: MutableList<MovieModel> = mutableListOf()

    private val popularMoviesLayoutManager by lazy {
       //GridLayoutManager(this, 2)
       LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    private val popularMoviesRecyclerAdapter by lazy {
        MoviesRecyclerAdapter(this, popularMoviesList)
    }

    private val topRatedMoviesLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    private val topRatedMoviesRecyclerAdapter by lazy {
        MoviesRecyclerAdapter(this, topRatedMoviesList)
    }

    private val incomingMoviesLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    private val incomingMoviesRecyclerAdapter by lazy {
        MoviesRecyclerAdapter(this, incomingMoviesList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_all_movies)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        //val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_fav, R.id.nav_slideshow
            ), drawerLayout
        )*/
        //val toggle = ActionBarDrawerToggle (this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        //drawerLayout.addDrawerListener(toggle)
        //toggle.syncState()

        val toggle = ActionBarDrawerToggle (this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        getInformationFromAPI()

        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
        /*val recyclerListMoviesId = findViewById<RecyclerView>(R.id.recyclerListMovies)
        recyclerListMoviesId.layoutManager = moviesLayoutManager
        recyclerListMoviesId.adapter = moviesRecyclerAdapter*/
    }

    private fun getInformationFromAPI() {
        getPopularMoviesList()
        getTopRatedMoviesList()
        getIncomingMoviesList()
    }

    private fun getPopularMoviesList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getPopularMoviesList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //TODO show error
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                for (i in response.body()?.results!!){
                    popularMoviesList.add(i)
                }

                val recyclerListMoviesId = findViewById<RecyclerView>(R.id.recyclerListPopularMovies)
                recyclerListMoviesId.layoutManager = popularMoviesLayoutManager
                recyclerListMoviesId.adapter = popularMoviesRecyclerAdapter
            }

        })
    }

    private fun getTopRatedMoviesList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getTopRatedMoviesList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //TODO show error
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                for (i in response.body()?.results!!){
                    topRatedMoviesList.add(i)
                }

                val recyclerTopRatedListMoviesId = findViewById<RecyclerView>(R.id.recyclerListTopRatedMovies)
                recyclerTopRatedListMoviesId.layoutManager = topRatedMoviesLayoutManager
                recyclerTopRatedListMoviesId.adapter = topRatedMoviesRecyclerAdapter
            }

        })
    }

    private fun getIncomingMoviesList() {
        RetrofitClient.buildService(MoviesAPI::class.java).getIncomingRatedMoviesList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                //TODO show error
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                for (i in response.body()?.results!!){
                    incomingMoviesList.add(i)
                }

                val recyclerIncomingListMoviesId = findViewById<RecyclerView>(R.id.recyclerListIncomingMovies)
                recyclerIncomingListMoviesId.layoutManager = incomingMoviesLayoutManager
                recyclerIncomingListMoviesId.adapter = incomingMoviesRecyclerAdapter
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_home -> {
                getInformationFromAPI()
            }
            R.id.nav_fav -> {
                val intentFavourite = Intent(this, FavouriteMoviesActivity::class.java)
                startActivity(intentFavourite)
            }
        }
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}