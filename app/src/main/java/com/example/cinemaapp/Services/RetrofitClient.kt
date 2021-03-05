package com.example.cinemaapp.Services

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private var interceptor = StethoInterceptor()
    private var okHttp = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

    private fun getClient(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

    fun <T> buildService(contract: Class<T>): T {
        return getClient().create(contract)
    }
}