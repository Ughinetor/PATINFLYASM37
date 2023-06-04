package com.example.patinflyasm37.Users

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.*


class UserRepository {

    fun getAllUsers(context: Context, userDao: UserDAO) = CoroutineScope(Dispatchers.Default).async {
        return@async userDao.getAllUsers()
    }


   suspend fun getUser(context: Context,userDao: UserDAO,mail : String): User? {
        return userDao.getUserByEmail(mail)
    }



    fun insertUser(context: Context, userDao: UserDAO,user: User) = CoroutineScope(Dispatchers.Default).async {

        try {
            return@async userDao.insertUser(user)
        } catch (e: SQLiteConstraintException) {
            Log.d(UserRepository::class.simpleName, "Unique value error")
            return@async LinkedList<User>()
        }
    }
}