package com.example.myapppavor.modeldata


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class DetailFavoriteEvent(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val date: String

): Parcelable

