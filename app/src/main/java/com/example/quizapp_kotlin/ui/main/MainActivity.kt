package com.example.quizapp_kotlin.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp_kotlin.R
import com.example.quizapp_kotlin.custom.QuizView
import com.example.quizapp_kotlin.custom.QuizViewModel
import com.example.quizapp_kotlin.ui.list.ListActivity
import com.example.quizapp_kotlin.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private var quizView: QuizView? = null
    private lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        quizView = findViewById(R.id.quizView)

        quizViewModel.states.observe(this, Observer {
            if (it.size==4){
                quizView?.setData(it)
            }else{
                Toast.makeText(this,"Add more states",Toast.LENGTH_SHORT).show()
            }
        })

        val optionsClickListener = object :  QuizView.OptionsClickListener {
            override fun onClick(result: Boolean) {
                updateResult(result)
            }
        }

        quizView?.setOnOptionsClickListener(optionsClickListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater =  menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        when(id){
            R.id.list -> {
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateResult(result: Boolean){
        if(result){
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show()
        }
        quizViewModel.refreshGame()
        quizView?.reset()
    }
}