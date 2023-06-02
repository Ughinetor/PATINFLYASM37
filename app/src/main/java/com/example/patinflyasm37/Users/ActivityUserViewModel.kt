package com.example.patinflyasm37.Users


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class ActivityUserViewModel(app:Application): AndroidViewModel(app) {
    lateinit var allUsers : MutableLiveData<List<User>?>

    init {
        allUsers= MutableLiveData()
    }

    fun getAllUsers(){
        val userDao= AppDatabase.getInstance((getApplication()))?.userDao()
        val list= userDao?.getAllUsers()

        allUsers.postValue(list)
    }

    fun getAllUsersObservers(): MutableLiveData<List<User>?>{
        return allUsers
    }

    fun insertUser(entity: User){
        val userDao= AppDatabase.getInstance((getApplication()))?.userDao()
        userDao?.insertUser(entity)
    }

    fun updateUser(entity: User){
        val userDao= AppDatabase.getInstance((getApplication()))?.userDao()
        userDao?.updateUser(entity)
    }
}