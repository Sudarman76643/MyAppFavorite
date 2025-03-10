package com.example.myapppavor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapppavor.databinding.ItemFinishedEventBinding
import com.example.myapppavor.remote.response.ListEventsItem

class FinishedEventAdapter(private val onItemClick: (ListEventsItem) -> Unit) :
    RecyclerView.Adapter<FinishedEventAdapter.FinishedEventViewHolder>() {

    private val eventList = mutableListOf<ListEventsItem>()

    fun setData(newList: List<ListEventsItem>) {
        eventList.clear()
        eventList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishedEventViewHolder {
        val binding = ItemFinishedEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FinishedEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FinishedEventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int = eventList.size

    inner class FinishedEventViewHolder(private val binding: ItemFinishedEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: ListEventsItem) {
            binding.tvEventTitle.text = event.name
            binding.tvEventDate.text = "📅 ${event.beginTime} - ${event.endTime}"
            binding.tvEventLocation.text = "📍 ${event.cityName}"
            binding.tvEventQuota.text = "👥 ${event.registrants} / ${event.quota}"

            Glide.with(binding.root.context)
                .load(event.mediaCover)
                .into(binding.ivEventImage)

            binding.root.setOnClickListener {
                onItemClick(event)
            }
        }
    }
}
