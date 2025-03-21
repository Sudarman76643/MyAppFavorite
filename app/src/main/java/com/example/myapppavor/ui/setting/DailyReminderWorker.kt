package com.example.myapppavor.ui.setting

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapppavor.MainActivity
import com.example.myapppavor.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class DailyReminderWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        fetchEventAndNotify()
        return Result.success()
    }

    private fun fetchEventAndNotify() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://event-api.dicoding.dev/events?active=1&limit=1")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("DailyReminderWorker", "Gagal mengambil event: ${e.message}")
                sendNotification("Gagal mengambil event. Periksa koneksi internet.")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        Log.e("DailyReminderWorker", "Respons API gagal: ${it.code}")
                        sendNotification("Event tidak tersedia.")
                        return
                    }

                    val jsonData = it.body?.string()
                    if (jsonData.isNullOrEmpty()) {
                        Log.e("DailyReminderWorker", "Data API kosong")
                        sendNotification("Event tidak tersedia.")
                        return
                    }

                    try {
                        val jsonArray = JSONObject(jsonData).getJSONArray("events")
                        if (jsonArray.length() > 0) {
                            val eventObj = jsonArray.getJSONObject(0)
                            val eventName = eventObj.optString("name", "Nama tidak tersedia")
                            val eventDate = eventObj.optString("date", "Tanggal tidak tersedia")
                            val eventInfo = "$eventName - $eventDate"
                            sendNotification(eventInfo)
                        } else {
                            sendNotification("Tidak ada event aktif saat ini.")
                        }
                    } catch (e: Exception) {
                        Log.e("DailyReminderWorker", "Error parsing JSON: ${e.message}")
                        sendNotification("Gagal memproses data event.")
                    }
                }
            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun sendNotification(eventInfo: String) {
        val channelId = "daily_reminder_channel"
        val notificationId = 1

        val manager = applicationContext.getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Daily Reminder",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            }
            manager.createNotificationChannel(channel)
        }

        // Tambahkan suara notifikasi
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // Intent agar notifikasi bisa diklik
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle("Event Terdekat")
            .setContentText(eventInfo)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSound(soundUri)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()


        NotificationManagerCompat.from(applicationContext).notify(notificationId, notification)
    }
}