package com.udacity.asteroidradar.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.Repistory.Repistory
import com.udacity.asteroidradar.database.AsteroidDatabase
import retrofit2.HttpException
//this worker used to update db content daily when ever network is available
class KeepupWorker (appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params){
    companion object{
      const val  WORK_NAME="Keepworker"
    }
            override suspend fun doWork(): Result {
                val database = AsteroidDatabase.getInstance(applicationContext).asteroiddoa
                val repository = Repistory(database)
                return try {
                    repository.refersh()
                    Result.success()
                } catch (e: HttpException) {
                    Result.retry()
                }
            }
    }
