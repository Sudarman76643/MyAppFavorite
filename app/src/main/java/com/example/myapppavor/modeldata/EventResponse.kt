package com.example.myapppavor.modeldata

import com.example.myapppavor.entity.FavoriteEvent

data class EventResponse(
    val error: Boolean,
    val message: String,
    val event: FavoriteEvent?
)
