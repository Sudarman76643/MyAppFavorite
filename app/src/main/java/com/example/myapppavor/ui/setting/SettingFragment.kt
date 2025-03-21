package com.example.myapppavor.ui.setting

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
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
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myapppavor.receiver.NotifReceiver
import java.util.concurrent.TimeUnit

class SettingFragment : Fragment() {

    private lateinit var settingViewModel: SettingViewModel
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        val viewModelFactory = ViewModelFactory(pref)
        settingViewModel =
            ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)

        settingViewModel.getThemeSettings()
            .observe(viewLifecycleOwner, Observer { isDarkModeActive ->
                binding.switchTheme.isChecked = isDarkModeActive
                updateTheme(isDarkModeActive)
            })

        settingViewModel.getReminderSetting()
            .observe(viewLifecycleOwner, Observer { isReminderActive ->
                binding.switchReminder.isChecked = isReminderActive
            })

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                settingViewModel.saveThemeSetting(isChecked)
            }
        }

        binding.switchReminder.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                settingViewModel.saveReminderSetting(isChecked)
                scheduleDailyReminder(isChecked)
                configureWorkManager(isChecked)
            }
        }

        return binding.root
    }

    private fun updateTheme(isDarkMode: Boolean) {
        val mode = if (isDarkMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun scheduleDailyReminder(isActive: Boolean) {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), NotifReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(), 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                val settingsIntent = Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                    data = Uri.parse("package:${requireContext().packageName}")
                }
                startActivity(settingsIntent)
                return
            }
        }

        if (isActive) {
            val triggerTime = System.currentTimeMillis() + 20 * 1000
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
        } else {
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun configureWorkManager(isActive: Boolean) {
        val workManager = WorkManager.getInstance(requireContext())
        if (isActive) {
            val workRequest = PeriodicWorkRequestBuilder<DailyReminderWorker>(1, TimeUnit.DAYS)
                .build()
            workManager.enqueueUniquePeriodicWork(
                "daily_reminder_work",
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest
            )
        } else {
            workManager.cancelUniqueWork("daily_reminder_work")
        }
    }
}