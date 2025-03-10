package com.example.myapppavor.remote.retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UpcomingEventConfig {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://event-api.dicoding.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val upcomingEventService: UpcomingEventService = retrofit.create(UpcomingEventService::class.java)
}
