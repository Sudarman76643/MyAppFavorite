package com.example.myapppavor.ui.detailEvent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapppavor.R
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

        Log.d("DetailEventFragment", "Image URL: ${args.imageUrl}")

        binding.tvEventTitle.text = args.title
        binding.tvEventDescription.text = HtmlCompat.fromHtml(args.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.tvEventLocation.text = if (args.location.startsWith("http")) {
            "Lokasi tersedia di tautan berikut:\n${args.location}"
        } else {
            "📍 ${args.location}"
        }
        binding.tvEventDate.text = "🕒 ${args.date}"

        val imageUrl = if (args.imageUrl.isNullOrEmpty()) {
            "https://event-api.dicoding.dev/"
        } else {
            args.imageUrl
        }

        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.event1)
            .error(R.drawable.warning)
            .fitCenter()
            .into(binding.ivEventImage)

        binding.btnJoinEvent.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnBack.setOnClickListener {
            Log.d("DetailEventFragment", "Tombol back ditekan")
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
