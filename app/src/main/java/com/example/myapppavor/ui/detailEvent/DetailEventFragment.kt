package com.example.myapppavor.ui.detailEvent

import android.os.Bundle
<<<<<<< HEAD
import android.text.Html
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
=======
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
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

<<<<<<< HEAD
        val title = args.title
        val descriptionString = args.description
=======
        val eventId = args.eventId
        val title = args.title
        val description = args.description
>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
        val location = args.location
        val imageUrl = args.imageUrl
        val date = args.date

<<<<<<< HEAD
        Log.d("DetailEventFragment", "Received imageUrl: $imageUrl")

                binding.tvEventTitle.text = title
        binding.tvEventDescription.text = HtmlCompat.fromHtml(descriptionString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val formattedLocation = if (location.startsWith("http")) {
            "Lokasi tersedia di tautan berikut:\n$location"
        } else {
            "📍 $location"
        }

        binding.tvEventLocation.text = formattedLocation
        binding.tvEventDate.text = "🕒 $date"





        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(100, 100)
            .placeholder(R.drawable.event1)
            .error(R.drawable.warning)
            .into(binding.ivEventImage)

        Log.d("DetailEventFragment", "Received imageUrl: $imageUrl")


        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }


=======
        // Set data ke UI
        binding.tvEventTitle.text = title
        binding.tvEventDescription.text = description
        binding.tvEventLocation.text = location
        binding.tvEventDate.text = date
        Glide.with(this).load(imageUrl).into(binding.ivEventImage)
    }

>>>>>>> 4598020fff13130edf8069f290fc078f9f32ce1f
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
