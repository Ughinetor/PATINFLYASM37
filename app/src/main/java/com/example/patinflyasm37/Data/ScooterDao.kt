package com.example.patinflyasm37.Data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ScooterDao {

    @Query("SELECT * FROM scooters")
    fun getAllScooter() : List<Scooter>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScooterList(scooters: List<Scooter>)
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg scooter: Scooter)// Add the 'suspend' modifier for coroutines

    @Delete
    suspend fun delete(scooter: Scooter) // Add the 'suspend' modifier for coroutines
}