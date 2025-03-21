package com.example.myapppavor.remote.retrofit

import com.example.myapppavor.remote.response.NotificationResponse
import retrofit2.http.GET

interface EventNotifService {
    @GET("events?active=1&limit=1")
    suspend fun getNearestEvent(): NotificationResponse
}