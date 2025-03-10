package com.example.myapppavor.remote.retrofit

import com.example.myapppavor.remote.response.GetListOfEventsResponse
import retrofit2.Response
import retrofit2.http.GET

interface FavoriteService {
    @GET("events")
    suspend fun getUpcomingEvents(): Response<GetListOfEventsResponse>
}
