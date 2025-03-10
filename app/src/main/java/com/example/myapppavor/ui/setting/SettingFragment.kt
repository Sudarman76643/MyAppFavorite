package com.example.myapppavor.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapppavor.databinding.FragmentSettingBinding
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatDelegate

class SettingFragment : Fragment() {

    private lateinit var settingViewModel: SettingViewModel
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        // Mengambil instance SettingPreferences dengan dataStore yang sesuai
        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        val viewModelFactory = ViewModelFactory(pref)
        settingViewModel = ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)


        settingViewModel.getThemeSettings().observe(viewLifecycleOwner, Observer { isDarkModeActive ->
            binding.switchTheme.isChecked = isDarkModeActive

            updateTheme(isDarkModeActive)
        })

        // Menyimpan status tema dalam coroutine
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                settingViewModel.saveThemeSetting(isChecked)
            }
        }

        return binding.root
    }

    // Fungsi untuk memperbarui tema aplikasi
    private fun updateTheme(isDarkMode: Boolean) {
        val mode = if (isDarkMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)  // Mengubah mode tema aplikasi
    }
}
