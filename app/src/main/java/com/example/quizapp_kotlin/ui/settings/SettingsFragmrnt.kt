package com.example.quizapp_kotlin.ui.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.quizapp_kotlin.R
import com.example.quizapp_kotlin.notifications.Notifications
import com.example.quizapp_kotlin.notifications.NotificationsWorker
import java.util.*
import java.util.concurrent.TimeUnit

class SettingsFragmrnt: PreferenceFragmentCompat() {

    private val work = "Notification work"

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val notificationPreference: SwitchPreference? = findPreference("Notifications_preference")

        notificationPreference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->

            val current = System.currentTimeMillis()
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 20)
            calendar.set(Calendar.MINUTE, 45)

            if (calendar.timeInMillis < current) {
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }

            val workManager = WorkManager.getInstance(requireActivity())
            val builder = PeriodicWorkRequest.Builder(NotificationsWorker::class.java, 1, TimeUnit.DAYS)
            builder.setInitialDelay(calendar.timeInMillis - current, TimeUnit.MILLISECONDS)

            val check = newValue as Boolean
            if (check){
                workManager.enqueueUniquePeriodicWork(
                    work,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    builder.build()
                )}
                else{
                    workManager.cancelUniqueWork(work)
                }
            true
        }
    }
}