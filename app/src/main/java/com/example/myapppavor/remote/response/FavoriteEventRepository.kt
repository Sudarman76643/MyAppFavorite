package com.example.myapppavor.remote.response

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapppavor.entity.FavoriteEvent
import com.example.myapppavor.remote.retrofit.FavoriteService
import com.example.myapppavor.room.FavoriteEventDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteEventRepository(private val dao: FavoriteEventDao, private val apiService: FavoriteService) {

    val allFavorites: LiveData<List<FavoriteEvent>> = dao.getAllFavorites()

    suspend fun insert(event: FavoriteEvent) {
        dao.insert(event)
    }

    suspend fun delete(event: FavoriteEvent) {
        dao.delete(event)
    }

    suspend fun fetchEventsFromApi() {
        withContext(Dispatchers.IO) {
            try {
                Log.d("FavoriteEventRepository", "Fetching events from API...")

                val response = apiService.getUpcomingEvents()
                Log.d("API Response", "Response body: ${response.body()}")

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        Log.d("FavoriteEventRepository", "API Response: ${responseBody.listEvents.size} events found")

                        val events = responseBody.listEvents.map { event ->
                            FavoriteEvent(
                                id = event.id.toLong(),
                                title = event.name,
                                description = event.description,
                                imageUrl = event.imageLogo

                            )
                        }

                        dao.insertAll(events)
                        Log.d("FavoriteEventRepository", "Successfully saved ${events.size} events to database")
                    } else {
                        Log.e("FavoriteEventRepository", "API response error: ${responseBody?.message}")
                    }
                } else {
                    Log.e("FavoriteEventRepository", "API request failed: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("FavoriteEventRepository", "Error fetching events: ${e.message}")
            }
        }
    }
}
