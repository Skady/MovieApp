package com.example.cinemaapp.Models

object DataManager {
    val movies = ArrayList<MovieInfo>()

    init {
        initializeMovies()
    }

    private fun initializeMovies() {
        var movie = MovieInfo(
                "soy leyenda",
            "https://image.tmdb.org/t/p/original/uwjaCH7PiWrkz7oWJ4fcL3xGrb0.jpg",
                "A portal transports Lt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "7.3"
                )
        movies.add(movie)

        movie = MovieInfo(
                "Batallas letales",
            "https://image.tmdb.org/t/p/original/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
                "A teenage girl living with schizophrenia begins to suspect her neighbor has kidnapped a child. Her parents try desperately to help her live a normal life, without exposing their own tragic secrets, and the only person who believes her is Caleb – a boy she isn’t even sure exists.",
                "6.3"
        )
        movies.add(movie)

        movie = MovieInfo(
                "Harry potter",
            "https://image.tmdb.org/t/p/original/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg",
                "In the near future, a drone pilot is sent into a deadly militarized zone and must work with an android officer to locate a doomsday device.",
                "5.3"
        )
        movies.add(movie)

        movie = MovieInfo(
                "Batallas letales",
            "https://image.tmdb.org/t/p/original/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "A portal transports Lt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "9.3"
        )
        movies.add(movie)

        movie = MovieInfo(
            "Harry potter",
            "https://image.tmdb.org/t/p/original/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
            "A portal transports Lt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "5.3"
        )
        movies.add(movie)

        movie = MovieInfo(
            "Batallas letales",
            "https://image.tmdb.org/t/p/original/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
            "A portal transports Lt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "9.3"
        )
        movies.add(movie)
    }

    /*
//To add to favourite list
    public fun addProduct(provider: ProviderInfo, productName: String, productType: String, productDescription: String): Int {
        val product = ProductInfo(provider, productName, productType, productDescription)
        products.add(product)
        return products.lastIndex
    }
*/
/*
// To search for details
    public fun findProduct(provider: ProviderInfo, productName: String, productType: String, productDescription: String): ProductInfo? {
        for(product in products)
            if(provider == product.provider && productName == product.name && productType == product.type && productDescription == product.description)
                return product
        return null
    }

 */
}
