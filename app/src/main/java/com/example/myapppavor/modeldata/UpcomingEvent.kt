package com.example.myapppavor.modeldata

data class UpcomingEvent(
    val id: Int,
    val name: String,
    val summary: String,
    val imageLogo: String,
    val mediaCover: String,
    val category: String,
    val ownerName: String,
    val cityName: String,
    val quota: Int,
    val registrants: Int,
    val beginTime: String,
    val endTime: String,
    val link: String
)