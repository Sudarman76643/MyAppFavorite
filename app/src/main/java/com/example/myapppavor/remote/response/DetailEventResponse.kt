package com.example.myapppavor.remote.response

import com.google.gson.annotations.SerializedName

data class DetailEventResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("event")
	val event: EventDetail?
)
<<<<<<< HEAD
=======

>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
data class EventDetail(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("date")
	val date: String
)
