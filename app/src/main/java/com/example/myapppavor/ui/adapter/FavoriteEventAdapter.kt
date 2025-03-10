package com.example.myapppavor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapppavor.R
import com.example.myapppavor.entity.FavoriteEvent

class FavoriteEventAdapter(
    private val onClick: (FavoriteEvent) -> Unit,
    private val onRemoveFavorite: (FavoriteEvent) -> Unit
) : ListAdapter<FavoriteEvent, FavoriteEventAdapter.FavoriteEventViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteEventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_event, parent, false)
        return FavoriteEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteEventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
        holder.itemView.setOnClickListener { onClick(event) }
    }

    inner class FavoriteEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val description: TextView = itemView.findViewById(R.id.tvDescription)
        private val image: ImageView = itemView.findViewById(R.id.ivEventImage)
        private val ivFavorite: ImageView = itemView.findViewById(R.id.ivFavorite)

        fun bind(event: FavoriteEvent) {
            title.text = event.title
            description.text = event.description
            Glide.with(itemView.context).load(event.imageUrl).into(image)

            ivFavorite.setOnClickListener {
                onRemoveFavorite(event)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEvent>() {
            override fun areItemsTheSame(oldItem: FavoriteEvent, newItem: FavoriteEvent): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteEvent, newItem: FavoriteEvent): Boolean {
                return oldItem == newItem
            }
        }
    }
}

