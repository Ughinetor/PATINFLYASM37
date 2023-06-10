package com.example.patinflyasm37.Data

import androidx.room.*


@Dao
interface UserDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User?)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>?

    @Update
    fun updateUser(user: User?)

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): User?

    @Delete
    fun deleteUser(user: User?)
}