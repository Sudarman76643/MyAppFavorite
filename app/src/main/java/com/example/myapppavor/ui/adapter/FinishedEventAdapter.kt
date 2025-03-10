package com.example.myapppavor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapppavor.R
import com.example.myapppavor.databinding.ItemFinishedEventBinding
import com.example.myapppavor.remote.response.ListEventsItem

class FinishedEventAdapter(
    private val onItemClick: (ListEventsItem) -> Unit,
    private val onFavoriteClick: (ListEventsItem, Boolean) -> Unit // Tambah callback untuk favorite
) : RecyclerView.Adapter<FinishedEventAdapter.FinishedEventViewHolder>() {

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

        private var isFavorite = false

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

            // Atur default icon ke abu-abu
            binding.ivFavorite.setImageResource(R.drawable.love_pecah)
            binding.ivFavorite.setColorFilter(binding.root.context.getColor(android.R.color.darker_gray))

            binding.ivFavorite.setOnClickListener {
                isFavorite = !isFavorite
                if (isFavorite) {
                    binding.ivFavorite.setImageResource(R.drawable.love)
                    binding.ivFavorite.setColorFilter(binding.root.context.getColor(android.R.color.holo_red_light))
                } else {
                    binding.ivFavorite.setImageResource(R.drawable.love_pecah)
                    binding.ivFavorite.setColorFilter(binding.root.context.getColor(android.R.color.darker_gray))
                }
                onFavoriteClick(event, isFavorite) // Kirim event dan status favorite
            }
        }
    }
}
