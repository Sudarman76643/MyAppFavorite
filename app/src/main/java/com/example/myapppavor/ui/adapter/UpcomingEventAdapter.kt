package com.example.myapppavor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapppavor.R
import com.example.myapppavor.remote.response.ListEventsItem

class UpcomingEventAdapter(
    private val onRegisterClick: (ListEventsItem) -> Unit
) : RecyclerView.Adapter<UpcomingEventAdapter.EventViewHolder>() {

    private var events: List<ListEventsItem> = emptyList()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgEventCover: ImageView = itemView.findViewById(R.id.imgEventCover)
        private val imgCategoryIcon: ImageView = itemView.findViewById(R.id.imgCategoryIcon)
        private val imgLove: ImageView = itemView.findViewById(R.id.imgLove) // Tambahkan ini
        private val tvEventName: TextView = itemView.findViewById(R.id.tvEventName)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        private val tvEventTime: TextView = itemView.findViewById(R.id.tvEventTime)
        private val tvEventLocation: TextView = itemView.findViewById(R.id.tvEventLocation)
        private val progressRegistrants: ProgressBar = itemView.findViewById(R.id.progressRegistrants)
        private val tvQuota: TextView = itemView.findViewById(R.id.tvQuota)
        private val btnRegister: Button = itemView.findViewById(R.id.btnRegister)

        fun bind(event: ListEventsItem) {
            imgEventCover.load(event.mediaCover) {
<<<<<<< HEAD
                placeholder(R.drawable.event1)
=======
                placeholder(R.drawable.warning)
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
                error(R.drawable.warning)
            }

            event.imageLogo?.let {
                imgCategoryIcon.load(it) {
                    error(R.drawable.category)
                }
            }

            tvEventName.text = event.name
            tvCategory.text = event.category
            tvEventTime.text = "${event.beginTime} - ${event.endTime}"
            tvEventLocation.text = event.cityName
            tvQuota.text = "${event.registrants}/${event.quota}"

            progressRegistrants.max = event.quota
            progressRegistrants.progress = event.registrants

            btnRegister.setOnClickListener {
                onRegisterClick(event)
            }

<<<<<<< HEAD
=======
            // Atur klik pada imgLove
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
            imgLove.setOnClickListener {
                val isFavorite = it.tag as? Boolean ?: false

                if (isFavorite) {
                    imgLove.setImageResource(R.drawable.favor)
                    imgLove.setColorFilter(ContextCompat.getColor(itemView.context, android.R.color.darker_gray))
                } else {
                    imgLove.setImageResource(R.drawable.love)
                    imgLove.setColorFilter(ContextCompat.getColor(itemView.context, android.R.color.holo_red_light))
                }

                it.tag = !isFavorite
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcoming_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size

    fun setData(newEvents: List<ListEventsItem>) {
        events = newEvents
        notifyDataSetChanged()
    }
}
