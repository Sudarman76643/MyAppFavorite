package com.example.myapppavor.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FavoriteConfig {
    private const val BASE_URL = "https://event-api.dicoding.dev/"

    val instance: FavoriteService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(FavoriteService::class.java)
    }
}
