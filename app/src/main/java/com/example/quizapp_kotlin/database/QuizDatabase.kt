package com.example.quizapp_kotlin.database

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp_kotlin.data.Quiz
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
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
                        .addCallback(object : RoomDatabase.Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                executorService.execute {
                                    populateDb(context.assets, getInstance(context))
                                }
                            }
                        })
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        fun populateDb(assetManager: AssetManager, quizDatabase: QuizDatabase){
            var jsonString: String? = null
            try {
                jsonString = assetManager.open("state-capital.json").bufferedReader().use {  it.readText()}
            }catch (ioException: IOException){
                ioException.printStackTrace()
            }

            val quizDao = quizDatabase.dao
            try {
                val states = JSONObject(jsonString!!)
                val section = states.getJSONObject("sections")
                parseJson(section.getJSONArray("States (A-L)"), quizDao)
                parseJson(section.getJSONArray("States (M-Z)"), quizDao)
                parseJson(section.getJSONArray("Union Territories"), quizDao)
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }

        fun parseJson(jsonArray: JSONArray, quizDao: QuizDao){
            try {
                for (i in 0 until jsonArray.length()){
                    val state = jsonArray.getJSONObject(i)
                    val stateName = state.getString("key")
                    val capitalName = state.getString("val")
                    val newState = Quiz(stateName, capitalName)
                    quizDao.insertState(newState)
                }
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }
    }
}