package com.example.quizapp_kotlin.custom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.net.URI.create

class CustomLiveData(count: MutableLiveData<Int>, increment: MutableLiveData<Int>): MediatorLiveData<Pair<Int, Int>>() {

    init {
        addSource(count){
            if (it!=null)
            value = Pair(it, increment.value!!)
        }

        addSource(increment){
            if (it!=null){
                value = Pair(count.value!!, it)
            }
        }
    }
}