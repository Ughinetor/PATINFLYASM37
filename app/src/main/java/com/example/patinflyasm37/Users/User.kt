package com.example.patinflyasm37.Users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @PrimaryKey(autoGenerate = false)@ColumnInfo(name="email")
    val email: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,

    @ColumnInfo(name = "identification")
    val password: String,

    @ColumnInfo(name = "nationality")
    val nationality: String,

    @ColumnInfo(name = "kilometers_traveled")
    val kilometersTraveled: Double
)