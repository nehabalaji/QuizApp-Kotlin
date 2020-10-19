package com.example.quizapp_kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizapp_kotlin.data.Quiz
import java.util.concurrent.Executors

@Database(entities = [Quiz::class], version = 2, exportSchema = false)
abstract class QuizDatabase: RoomDatabase() {

    abstract val dao: QuizDao

    companion object{
        val executorService = Executors.newSingleThreadExecutor()

        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase{
            synchronized(this){
                var instance: QuizDatabase? = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(context.applicationContext, QuizDatabase::class.java, "QuizDatabase")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}