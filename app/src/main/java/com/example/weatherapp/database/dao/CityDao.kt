package com.example.weatherapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.weatherapp.database.entity.CityEntity

@Dao
interface CityDao {

    @Insert(onConflict = REPLACE)
    fun insertCity(cityEntity: CityEntity)

    @Query("SELECT * FROM cities WHERE name LIKE :name LIMIT 1")
    fun getCity(name: String): CityEntity?


    @Query("SELECT * FROM cities")
    fun getCities(): List<CityEntity>?
}