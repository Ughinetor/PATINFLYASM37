package com.example.patinflyasm37.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class, Scooter::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun scooterDao(): ScooterDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        public fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }



        val migration1to2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Perform the necessary SQL statements to update the schema
                database.execSQL("ALTER TABLE scooters ADD COLUMN last_maintenance_date String")
            }
        }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "application_database.db"
            ).fallbackToDestructiveMigration()
                .build()
    }
}
