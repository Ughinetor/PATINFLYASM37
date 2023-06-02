package com.example.patinflyasm37.Users

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScooterDao {
    @Query("SELECT * FROM scooter")
    fun getAll(): List<Scooter>

    @Insert
    fun insertAll(vararg scooters: Scooter)

    @Delete
    fun delete(scooter: Scooter)
}