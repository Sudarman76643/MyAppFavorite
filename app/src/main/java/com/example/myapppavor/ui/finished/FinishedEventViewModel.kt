package com.example.myapppavor.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapppavor.remote.response.ListEventsItem
import com.example.myapppavor.remote.retrofit.FinishedEventConfig
import kotlinx.coroutines.launch

class FinishedEventViewModel : ViewModel() {

    private val _allFinishedEvents = MutableLiveData<List<ListEventsItem>>()
    private val _filteredFinishedEvents = MutableLiveData<List<ListEventsItem>>()
    val finishedEvents: LiveData<List<ListEventsItem>> get() = _filteredFinishedEvents

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
}
