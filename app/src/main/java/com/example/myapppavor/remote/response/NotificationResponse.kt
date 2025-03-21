package com.example.myapppavor.remote.response

import com.google.gson.annotations.SerializedName

data class NotificationResponse(

	@field:SerializedName("listEvents")
	val listEvents: List<ListEventsItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

