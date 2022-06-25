package com.example.weatherapp

import android.app.Application
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.weatherapp.api.RetrofitClient
import com.example.weatherapp.database.AppDatabase
import com.example.weatherapp.work_manager.FetchTopCitiesWorker

class WeatherApp: Application() {

    private val fetchCitiesWorker = OneTimeWorkRequestBuilder<FetchTopCitiesWorker>().build()

    override fun onCreate() {
        super.onCreate()
        roomDatabase = AppDatabase.initialize(applicationContext)
        RetrofitClient.initClient()
        WorkManager.getInstance(applicationContext).enqueue(fetchCitiesWorker)
    }
    companion object {
        lateinit var roomDatabase: AppDatabase
            private set
    }
}