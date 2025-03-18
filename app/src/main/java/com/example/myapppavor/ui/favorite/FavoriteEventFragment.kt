package com.example.myapppavor.ui.favorite

import android.os.Bundle
<<<<<<< HEAD
import android.util.Log
=======
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import com.example.myapppavor.databinding.FragmentFavoriteBinding
import com.example.myapppavor.entity.FavoriteEvent
import com.example.myapppavor.modeldata.DetailFavoriteEvent
import com.example.myapppavor.ui.adapter.FavoriteEventAdapter

class FavoriteEventFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteEventViewModel by viewModels()
    private lateinit var adapter: FavoriteEventAdapter
<<<<<<< HEAD
    private var allEvents: List<FavoriteEvent> = listOf()
=======
    private var allEvents: List<FavoriteEvent> = listOf() // Store the original list of events
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

<<<<<<< HEAD
        adapter = FavoriteEventAdapter(
            onClick = { event -> navigateToDetail(event) },
            onRemoveFavorite = { event -> removeFromFavorites(event) }
        )
=======
        adapter = FavoriteEventAdapter { event ->
            navigateToDetail(event)
        }
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f

        binding.rvFavoriteEvents.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteEvents.adapter = adapter

<<<<<<< HEAD

        viewModel.favoriteEvents.observe(viewLifecycleOwner) { events ->
            allEvents = events
            adapter.submitList(events)
            binding.tvEmptyList.visibility = if (events.isEmpty()) View.VISIBLE else View.GONE
        }

        // Set up SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
=======
        viewModel.favoriteEvents.observe(viewLifecycleOwner) { events: List<FavoriteEvent> ->
            allEvents = events // Store all events to use later for searching
            adapter.submitList(events)
        }

        // Set up SearchView
        val searchView: SearchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterEvents(newText)
                return true
            }
        })
    }

<<<<<<< HEAD
    private fun removeFromFavorites(event: FavoriteEvent) {
        viewModel.deleteFavorite(event)
    }

    private fun filterEvents(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            allEvents
=======
    private fun filterEvents(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            allEvents // Show all events if query is empty
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
        } else {
            allEvents.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        adapter.submitList(filteredList)
    }

    private fun navigateToDetail(event: FavoriteEvent) {
<<<<<<< HEAD
        if (event.title.isNullOrEmpty() || event.description.isNullOrEmpty()) {
            Log.e("FavoriteEventFragment", "Event data kosong, tidak bisa navigasi")
            return
        }

=======
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
        val detailEvent = DetailFavoriteEvent(
            id = event.id.toString(),
            title = event.title,
            description = event.description,
            imageUrl = event.imageUrl,
            date = "12 Maret 2025, 10:00 WIB"
        )

        val action = FavoriteEventFragmentDirections
            .actionNavigationFavoriteToDetailEventFragment(
                detailEvent.id,
                detailEvent.title,
                detailEvent.description,
                detailEvent.imageUrl,
                detailEvent.date,
                date = "nilai default"
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
