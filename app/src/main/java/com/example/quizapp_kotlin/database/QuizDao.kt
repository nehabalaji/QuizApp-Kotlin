package com.example.quizapp_kotlin.database

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
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

    @RawQuery(observedEntities = [Quiz::class])
    fun getAllStates(supportSQLiteQuery: SupportSQLiteQuery): DataSource.Factory<Int, Quiz>

    @Query("SELECT * FROM StateAndCapital ORDER BY RANDOM() LIMIT 4")
    fun getQuizState(): List<Quiz>

    @Query("SELECT * FROM StateAndCapital ORDER BY RANDOM() LIMIT 1")
    fun getRandomState(): Quiz
}