package com.example.myapppavor.ui.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.myapppavor.entity.FavoriteEvent
import com.example.myapppavor.remote.response.FavoriteEventRepository
import com.example.myapppavor.remote.retrofit.FavoriteConfig
import com.example.myapppavor.room.FavoriteEventDatabase
import kotlinx.coroutines.launch

class FavoriteEventViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FavoriteEventRepository
    private val _favoriteEvents = MutableLiveData<List<FavoriteEvent>>()  // LiveData yang bisa diubah
    val favoriteEvents: LiveData<List<FavoriteEvent>> get() = _favoriteEvents


    fun deleteFavorite(event: FavoriteEvent) {
        _favoriteEvents.value = _favoriteEvents.value?.filter { it.id != event.id }
    }

    init {
        val dao = FavoriteEventDatabase.getDatabase(application).favoriteEventDao()
        val apiService = FavoriteConfig.instance
        repository = FavoriteEventRepository(dao, apiService)

        fetchEvents()
        observeDatabase()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            try {
                Log.d("FavoriteEventViewModel", "Fetching events from API...")
                repository.fetchEventsFromApi()
                Log.d("FavoriteEventViewModel", "Fetch successful")
            } catch (e: Exception) {
                Log.e("FavoriteEventViewModel", "Error fetching events: ${e.message}")
            }
        }
    }

    private fun observeDatabase() {
        repository.allFavorites.observeForever { events ->
            Log.d("FavoriteEventViewModel", "Data diperbarui, jumlah event: ${events.size}")
            _favoriteEvents.postValue(events)
        }
    }

    fun insert(event: FavoriteEvent) = viewModelScope.launch {
        repository.insert(event)
    }

    fun delete(event: FavoriteEvent) = viewModelScope.launch {
        repository.delete(event)
    }
}
