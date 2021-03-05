package com.example.cinemaapp.Services

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private const val BASE_URL_IMDB = "https://api.themoviedb.org/3/"
    private const val BASE_URL_OMDB = "http://www.omdbapi.com"
    private var interceptor = StethoInterceptor()
    private var okHttp = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

    private fun getClientIMDB(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_IMDB)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

    private fun getClientOMDB(): Retrofit {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_OMDB)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
        return retrofit as Retrofit
    }

    fun <T> buildServiceIMDB(contract: Class<T>): T {
        return getClientIMDB().create(contract)
    }

     fun <T> buildServiceOMDB(contract: Class<T>): T {
        return getClientOMDB().create(contract)
    }
}