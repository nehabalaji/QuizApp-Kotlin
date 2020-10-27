package com.example.quizapp_kotlin.custom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.quizapp_kotlin.data.Quiz
import com.example.quizapp_kotlin.database.QuizRepository
import java.util.concurrent.ExecutionException

class QuizViewModel(application: Application): AndroidViewModel(application) {

    val states = MutableLiveData<List<Quiz>>()
    private val quizRepository = QuizRepository.getRepository(application)!!

    init {
        loadGame()
    }

    private fun loadGame(){
        try {
            states.postValue(quizRepository.getQuizStates()?.get())
        }catch (e: InterruptedException){
            e.printStackTrace()
        }catch (e: ExecutionException){
            e.printStackTrace()
        }
    }

    fun refreshGame(){
        loadGame()
    }
}