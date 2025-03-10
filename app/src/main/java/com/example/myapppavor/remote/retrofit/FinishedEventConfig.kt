package com.example.myapppavor.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FinishedEventConfig {
    private const val BASE_URL = "https://event-api.dicoding.dev/"

    val finishedEventService: FinishedEventService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FinishedEventService::class.java)
    }
}
