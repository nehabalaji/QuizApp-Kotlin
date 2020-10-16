package com.example.quizapp_kotlin.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.quizapp_kotlin.R

class SettingsFragmrnt: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}