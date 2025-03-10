package com.example.myapppavor.ui.detailEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapppavor.databinding.FragmentDetailEventBinding

class DetailEventFragment : Fragment() {
    private var _binding: FragmentDetailEventBinding? = null
    private val binding get() = _binding!!

    private val args: DetailEventFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventId = args.eventId
        val title = args.title
        val description = args.description
        val location = args.location
        val imageUrl = args.imageUrl
        val date = args.date

        // Set data ke UI
        binding.tvEventTitle.text = title
        binding.tvEventDescription.text = description
        binding.tvEventLocation.text = location
        binding.tvEventDate.text = date
        Glide.with(this).load(imageUrl).into(binding.ivEventImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
