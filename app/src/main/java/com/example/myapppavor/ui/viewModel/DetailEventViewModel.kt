package com.example.myapppavor.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapppavor.modeldata.DetailFavoriteEvent

class DetailEventViewModel : ViewModel() {

    private val _detailEvent = MutableLiveData<DetailFavoriteEvent?>()
    val detailEvent: LiveData<DetailFavoriteEvent?> get() = _detailEvent

    fun getDetailEvent(eventId: String): LiveData<DetailFavoriteEvent?> {
        _detailEvent.value = DetailFavoriteEvent(
            id = eventId,
            title = "Sample Event",
            description = "This is a sample event description.",
            imageUrl = "https://event-api.dicoding.dev/",
            date = "10 March 2025"
        )
        return detailEvent
    }
}
