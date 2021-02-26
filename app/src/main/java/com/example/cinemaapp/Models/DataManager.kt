package com.example.cinemaapp.Models

object DataManager {
    var movies: MutableList<MovieModel> = mutableListOf()

    init {
        initializeMovies()
    }

    private fun initializeMovies() {

        var movie = MovieModel(
            "false",
            "/8tNX8s3j1O0eqilOQkuroRLyOZA.jpg",
            "458576",
            "en",
            "Monster Hunter",
            "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "3230.215",
            "/uwjaCH7PiWrkz7oWJ4fcL3xGrb0.jpg",
            "2020-12-03",
            "Monster Hunter",
            "false",
            "7.2",
            "703"
        )
        movies.add(movie)

        movie = MovieModel(
            "false",
            "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            "464052",
            "en",
            "Wonder Woman 1984",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "1666.794",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-12-16",
            "Wonder Woman 1984",
            "false",
            "6.9",
            "3867"
        )
        movies.add(movie)

        movie = MovieModel(
            "false",
            "/fQq1FWp1rC89xDrRMuyFJdFUdMd.jpg",
            "761053",
            "en",
            "Gabriel's Inferno Part III",
            "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.",
            "33.198",
            "/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
            "2020-11-19",
            "Gabriel's Inferno Part III",
            "false",
            "8.8",
            "723"
        )
        movies.add(movie)

    }

    public fun findMovie(originalTitle: String): MovieModel? {
        for(movie in movies)
            if(originalTitle == movie.original_title)
                return movie
        return null
    }

    public fun addMovie(
        adult: String?,
        backdrop_path: String?,
        id: String?,
        original_language: String?,
        original_title: String?,
        overview: String?,
        popularity: String?,
        poster_path: String?,
        release_date: String?,
        title: String?,
        video: String?,
        vote_average: String?,
        vote_count: String?
    ) {
        val newMovie = MovieModel(
            adult,
            backdrop_path,
            id,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count
        )
        movies.add(newMovie)
    }

    public fun removeMovie(originalTitle: String) {
        var moviesTMP: MutableList<MovieModel> = mutableListOf()

        for(movie in movies)
            if(originalTitle != movie.original_title)
                moviesTMP.add(movie)

        movies.clear()
        movies = moviesTMP
    }

}
