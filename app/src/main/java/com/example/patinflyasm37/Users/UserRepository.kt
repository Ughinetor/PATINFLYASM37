package com.example.patinflyasm37.Users

import android.util.Log

class UserRepository(private val userDAO: UserDAO) {

    fun insertUser(user: User) {
        userDAO?.insertUser(user)
    }

    fun getAllUsers(): List<User>? {
        return userDAO?.getAllUsers()
    }

    fun updateUser(user: User) {
        userDAO?.updateUser(user)
    }

    fun getUserByEmail(email: String): User? {
        return userDAO?.getUserByEmail(email)
    }

    fun deleteUser(user: User) {
        userDAO?.deleteUser(user)
    }
}