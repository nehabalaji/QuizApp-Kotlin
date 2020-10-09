package com.example.quizapp_kotlin.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp_kotlin.R

class AddActivity : AppCompatActivity() {

    val EXTRA_STATE_NAME = "extra_state_name_to_be_updated"
    val EXTRA_CAPITAL_NAME = "extra_capital_name_to_be_updated"
    val EXTRA_STATE_ID = "extra_state_id_to_be_updated"
    val UPDATE_STATE_REQUEST_CODE = 1
    val NEW_STATE_REQUEST_CODE = 2

    private lateinit var stateName: String
    private lateinit var capitalName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }
}