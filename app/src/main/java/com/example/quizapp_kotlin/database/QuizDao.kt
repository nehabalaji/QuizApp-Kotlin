package com.example.quizapp_kotlin.database

import androidx.paging.DataSource
import androidx.room.*
import com.example.quizapp_kotlin.data.Quiz

@Dao
interface QuizDao {
    @Query("SELECT * FROM StateAndCapital")
    fun getAllStates(): DataSource.Factory<Int, Quiz>

    @Insert
    fun insertState(quiz: Quiz)

    @Update
    fun updateState(quiz: Quiz)

    @Delete
    fun deleteState(quiz: Quiz)
}