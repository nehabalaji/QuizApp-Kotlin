package com.example.quizapp_kotlin.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.quizapp_kotlin.data.Quiz
import com.example.quizapp_kotlin.database.QuizRepository

class ListViewModel(application: Application): AndroidViewModel(application) {
    private var quizRepository = QuizRepository.getRepository(application)!!
    var statesList: LiveData<PagedList<Quiz>>

    init {
        statesList = quizRepository.getAllStates()
    }

    fun insertState(quiz: Quiz){
        quizRepository.insertState(quiz)
    }

    fun deleteState(quiz: Quiz){
        quizRepository.deleteState(quiz)
    }

}