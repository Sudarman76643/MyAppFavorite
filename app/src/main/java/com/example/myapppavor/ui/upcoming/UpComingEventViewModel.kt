package com.example.myapppavor.ui.upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapppavor.remote.response.ListEventsItem
import com.example.myapppavor.remote.retrofit.UpcomingEventConfig
import kotlinx.coroutines.launch

class UpcomingEventViewModel : ViewModel() {

    private val _upcomingEvents = MutableLiveData<List<ListEventsItem>>()
    val upcomingEvents: LiveData<List<ListEventsItem>> get() = _upcomingEvents

    fun getUpcomingEvents(token: String) {
        viewModelScope.launch {
            try {
                val response = UpcomingEventConfig.upcomingEventService.getUpcomingEvents(token)
                if (response.isSuccessful && response.body() != null) {
                    _upcomingEvents.postValue(response.body()!!.listEvents)
                } else {
                    Log.e("UpcomingEventViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}