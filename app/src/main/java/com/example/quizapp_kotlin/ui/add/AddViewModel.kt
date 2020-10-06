package com.example.quizapp_kotlin.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quizapp_kotlin.data.Quiz
import com.example.quizapp_kotlin.database.QuizRepository

class AddViewModel(application: Application): AndroidViewModel(application) {
    var quizRepository = QuizRepository.getRepository(application)!!

    fun insertState(quiz: Quiz){
        quizRepository.insertState(quiz)
    }

    fun updateState(quiz: Quiz){
        quizRepository.updateState(quiz)
    }
}