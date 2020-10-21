package com.example.quizapp_kotlin.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.quizapp_kotlin.R
import com.example.quizapp_kotlin.data.Quiz
import com.example.quizapp_kotlin.ui.main.MainActivity

class Notifications {

    private val primaryChannelID: String = "primary_channel_ID"
    private val notificationID: Int = 1

    fun getDailyNotifications(context: Context, quiz: Quiz){

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var primaryChannel = NotificationChannel(primaryChannelID, "Daily Quiz", NotificationManager.IMPORTANCE_DEFAULT)
            primaryChannel.enableLights(true)
            primaryChannel.lightColor = Color.CYAN
            notificationManager.createNotificationChannel(primaryChannel)
        }

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, notificationID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var title = "Daily Quiz Time!"
        var notificationContent = "What is the capital of "+quiz.stateName+" ?"

        val builder = NotificationCompat.Builder(context, primaryChannelID)
            .setContentTitle(title)
            .setContentText(notificationContent)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setAutoCancel(true)

        notificationManager.notify(notificationID, builder.build())
    }
}