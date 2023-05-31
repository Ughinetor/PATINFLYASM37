package com.example.patinflyasm37.Users

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDataBaseDao():UserDAO

    companion object{
        @Volatile
       var INSTANCE: UserDatabase? =null

        fun getInstance(context: Context): UserDatabase{
            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                    UserDatabase::class.java,
                    "UserDatabase")
                        .fallbackToDestructiveMigration().build()

                    INSTANCE= instance

                }
                return instance
            }

        }
    }
}