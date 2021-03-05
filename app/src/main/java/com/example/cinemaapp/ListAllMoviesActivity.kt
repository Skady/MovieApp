package com.example.cinemaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.Adapters.MoviesRecyclerAdapter
import androidx.lifecycle.Observer
import com.example.cinemaapp.ViewModels.ListAllMoviesViewModel
import androidx.lifecycle.ViewModelProvider
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_list_all_movies.*
import kotlinx.android.synthetic.main.content_main_list_all_movies.*

class ListAllMoviesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var context: Context

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: ListAllMoviesViewModel

    private val popularMoviesLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    private val topRatedMoviesLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    private val upcomingMoviesLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Stetho.initializeWithDefaults(this);

        setContentView(R.layout.activity_list_all_movies)

        context = this@ListAllMoviesActivity

        viewModel = ViewModelProvider(this).get(ListAllMoviesViewModel::class.java)

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
    }

    private fun getInformationFromAPI() {
        getPopularMoviesList()
        getTopRatedMoviesList()
        getUpcomingMoviesList()
    }

    private fun getPopularMoviesList() {
        viewModel.loadPopularMovieList()

        viewModel.popularMovieList.observe(this, Observer {
            recyclerListPopularMovies.adapter = MoviesRecyclerAdapter(this, it)
            recyclerListPopularMovies.layoutManager = popularMoviesLayoutManager
        })
    }

    private fun getTopRatedMoviesList() {
        viewModel.loadTopRatedMovieList()

        viewModel.topRatedMovieList.observe(this, Observer {
            recyclerListTopRatedMovies.adapter = MoviesRecyclerAdapter(this, it)
            recyclerListTopRatedMovies.layoutManager = topRatedMoviesLayoutManager
        })
    }

    private fun getUpcomingMoviesList() {
        viewModel.loadUpcomingMovieList()

        viewModel.upcomingMovieList.observe(this, Observer {
            recyclerListUpcomingMovies.adapter = MoviesRecyclerAdapter(this, it)
            recyclerListUpcomingMovies.layoutManager = upcomingMoviesLayoutManager
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
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}