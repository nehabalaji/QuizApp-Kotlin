package com.example.quizapp_kotlin.notifications

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.quizapp_kotlin.database.QuizRepository

class NotificationsWorker(@NonNull context: Context, @NonNull workerParameters: WorkerParameters): Worker(context.applicationContext, workerParameters) {

    private val quizRepo: QuizRepository = QuizRepository.getRepository(context.applicationContext as Application)!!


    override fun doWork(): Result {
       var quiz = quizRepo.getRandomState()
        return Result.success()
    }
}