package com.example.myapppavor.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class SettingViewModel(private val pref: SettingPreferences) : ViewModel() {

    // Mengonversi Flow<Boolean> menjadi LiveData<Boolean>
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    // Menyimpan status tema
    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        pref.saveThemeSetting(isDarkModeActive)
    }
}
