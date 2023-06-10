
package com.example.patinflyasm37.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.UUID

@Entity(tableName = "scooters")
data class Scooter(
    @PrimaryKey
    @SerializedName("uuid")
    val identifier: String,

    @ColumnInfo(name = "longitude")
    @SerializedName("longitude")
    val longitude: Double,

    @ColumnInfo(name = "latitude")
    @SerializedName("latitude")
    val latitude: Double,

    @ColumnInfo(name = "battery_level")
    @SerializedName("battery_level")
    val batteryLevel: Int,

    @ColumnInfo(name = "distance_traveled")
    @SerializedName("meters_use")
    val distanceTraveled: Double,

    @ColumnInfo(name = "incorporation_date")
    @SerializedName("date_create")
    val scooterIncorporationDate: String,

    @ColumnInfo(name = "last_maintenance_date")
    @SerializedName("date_last_maintenance")
    val lastMaintenanceDate: String?,

    @ColumnInfo(name = "status")
    @SerializedName("state")
    val status: String,

    @ColumnInfo(name = "available")
    @SerializedName("vacant")
    val available: Boolean
)