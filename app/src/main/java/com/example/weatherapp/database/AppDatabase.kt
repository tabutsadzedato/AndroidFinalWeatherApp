package com.example.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.database.dao.CityDao
import com.example.weatherapp.database.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object {
        fun initialize(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "weather-appino")
                .allowMainThreadQueries().build()
    }
}