package com.example.myapppavor.ui.finished

<<<<<<< HEAD
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
=======
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppavor.entity.FavoriteEvent
import com.example.myapppavor.remote.response.ListEventsItem
import com.example.myapppavor.remote.retrofit.FinishedEventConfig
import com.example.myapppavor.ui.adapter.FinishedEventAdapter
=======
import com.example.myapppavor.remote.response.ListEventsItem
import com.example.myapppavor.remote.retrofit.FinishedEventConfig
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
import kotlinx.coroutines.launch

class FinishedEventViewModel : ViewModel() {

    private val _allFinishedEvents = MutableLiveData<List<ListEventsItem>>()
    private val _filteredFinishedEvents = MutableLiveData<List<ListEventsItem>>()
    val finishedEvents: LiveData<List<ListEventsItem>> get() = _filteredFinishedEvents

<<<<<<< HEAD
    private val _favoriteEvents = MutableLiveData<List<ListEventsItem>>()
    val favoriteEvents: LiveData<List<ListEventsItem>> get() = _favoriteEvents

    private val favoriteList = mutableListOf<ListEventsItem>()

=======
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
    fun getFinishedEvents() {
        viewModelScope.launch {
            try {
                val response = FinishedEventConfig.finishedEventService.getFinishedEvents()
                if (!response.error) {
                    _allFinishedEvents.value = response.listEvents
                    _filteredFinishedEvents.value = response.listEvents
                } else {
                    _filteredFinishedEvents.value = emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _filteredFinishedEvents.value = emptyList()
            }
        }
    }

    fun searchEvents(query: String) {
        val filteredList = _allFinishedEvents.value?.filter { event ->
            event.name?.contains(query, ignoreCase = true) == true ||
                    event.category?.contains(query, ignoreCase = true) == true
        } ?: emptyList()

        _filteredFinishedEvents.value = filteredList
    }
<<<<<<< HEAD

    fun addToFavorites(event: ListEventsItem) {
        if (!favoriteList.contains(event)) {
            favoriteList.add(event)
            _favoriteEvents.value = favoriteList.toList()
        }
    }

    fun removeFromFavorites(event: ListEventsItem) {
        favoriteList.remove(event)
        _favoriteEvents.value = favoriteList.toList()
    }
}
=======
}
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
