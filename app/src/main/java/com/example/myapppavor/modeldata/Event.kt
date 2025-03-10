package com.example.myapppavor.modeldata

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("location") val location: String,
    @SerializedName("image") val image: String,
    @SerializedName("date") val date: String
)