package com.d3ifcool1062.newsapps.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.d3ifcool1062.newsapps.database.getDatabase
import com.d3ifcool1062.newsapps.repository.NewsRepository
import retrofit2.HttpException

class RefreshDataWork(context: Context, params : WorkerParameters):
    CoroutineWorker(context, params){

    companion object{
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = NewsRepository(database)
        return try {
            repository.refreshNews()
            Result.success()
        }catch (e: HttpException){
            Result.failure()
        }
    }
}