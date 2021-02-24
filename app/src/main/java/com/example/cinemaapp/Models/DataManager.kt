package com.example.cinemaapp.Models

class DataManager {
    val movies = ArrayList<MovieInfo>()

    init {
        initializeMovies()
    }

    private fun initializeMovies() {
        var movie = MovieInfo(
                "soy leyenda",
                "https://image.tmdb.org/t/p/original/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
                "A portal transports Lt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "7.3"
                )
        movies.add(movie)

        movie = MovieInfo(
                "Batallas letales",
                "https://image.tmdb.org/t/p/original/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
                "A portal transports Lt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "6.3"
        )
        movies.add(movie)

        movie = MovieInfo(
                "soy leyenda",
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
