package com.example.myapppavor.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NotifConfig {
    fun getApiService(): EventNotifService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(EventNotifService::class.java)
    }
}