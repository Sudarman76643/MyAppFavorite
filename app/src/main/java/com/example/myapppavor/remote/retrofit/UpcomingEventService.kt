package com.example.myapppavor.remote.retrofit

import com.example.myapppavor.remote.response.UpcomingEventsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UpcomingEventService {
    @GET("events?active=1")
    suspend fun getUpcomingEvents(
        @Header("Authorization") token: String
    ): Response<UpcomingEventsResponse>
}
