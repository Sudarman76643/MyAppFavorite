package com.example.myapppavor.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppavor.databinding.FragmentUpcomingBinding
import com.example.myapppavor.ui.adapter.UpcomingEventAdapter

class UpComingEventFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UpcomingEventViewModel by viewModels()
    private lateinit var adapter: UpcomingEventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UpcomingEventAdapter { event ->
            Toast.makeText(requireContext(), "Clicked: ${event.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvUpcomingEvents.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUpcomingEvents.adapter = adapter

        viewModel.upcomingEvents.observe(viewLifecycleOwner) { events ->
            events?.let {
                adapter.setData(it)
            }
        }

        viewModel.getUpcomingEvents("Bearer your_token_here")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
