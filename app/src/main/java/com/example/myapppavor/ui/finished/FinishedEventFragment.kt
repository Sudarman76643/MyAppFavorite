package com.example.myapppavor.ui.finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppavor.databinding.FragmentFinishedEventBinding
import com.example.myapppavor.ui.adapter.FinishedEventAdapter

class FinishedEventFragment : Fragment() {
<<<<<<< HEAD
=======

>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
    private var _binding: FragmentFinishedEventBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FinishedEventViewModel by viewModels()
    private lateinit var adapter: FinishedEventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishedEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

<<<<<<< HEAD
        adapter = FinishedEventAdapter(
            onItemClick = { event ->
                Toast.makeText(requireContext(), "Clicked: ${event.name}", Toast.LENGTH_SHORT).show()
            },
            onFavoriteClick = { event, isFavorite ->
                if (isFavorite) {
                    viewModel.addToFavorites(event)
                    Toast.makeText(requireContext(), "${event.name} ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.removeFromFavorites(event)
                    Toast.makeText(requireContext(), "${event.name} dihapus dari favorit", Toast.LENGTH_SHORT).show()
                }
            }
        )
=======
        adapter = FinishedEventAdapter { event ->
            Toast.makeText(requireContext(), "Clicked: ${event.name}", Toast.LENGTH_SHORT).show()
        }
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f

        binding.rvFinishedEvents.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFinishedEvents.adapter = adapter

        viewModel.finishedEvents.observe(viewLifecycleOwner) { events ->
            if (events != null) {
                adapter.setData(events)
            }
        }

        viewModel.getFinishedEvents()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchEvents(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchEvents(it) }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
