package com.example.quizapp_kotlin.custom

import android.app.Application
import androidx.arch.core.util.Function
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.quizapp_kotlin.data.Quiz
import com.example.quizapp_kotlin.database.QuizRepository
import java.util.concurrent.ExecutionException

class QuizViewModel(application: Application): AndroidViewModel(application) {

    lateinit var states: LiveData<List<Quiz>>
    private val quizRepository = QuizRepository.getRepository(application)!!
    val count = MutableLiveData<Int>()
    val increment = MutableLiveData<Int>()
    var i =0
    val trigger: CustomLiveData

    init {
        count.value = 4
        increment.value = i
        trigger = CustomLiveData(count, increment)
        loadGame()
    }

    private fun loadGame(){
        states = Transformations.switchMap(trigger, Function {
            quizRepository.getQuizStates(it.first)
        })
    }

    fun refreshGame(){
        i++
        increment.postValue(i)
        loadGame()
    }
}