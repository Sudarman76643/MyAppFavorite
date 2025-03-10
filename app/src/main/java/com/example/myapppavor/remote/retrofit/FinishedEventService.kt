package com.example.myapppavor.remote.retrofit


import com.example.myapppavor.remote.response.FinishedEventResponse
import retrofit2.http.GET

interface FinishedEventService {
    @GET("events?active=0")
    suspend fun getFinishedEvents(): FinishedEventResponse
}