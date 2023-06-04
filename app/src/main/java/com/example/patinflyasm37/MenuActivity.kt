package com.example.patinflyasm37

import android.content.ContentProvider
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.patinflyasm37.Users.AppDatabase
import com.example.patinflyasm37.Users.User
import com.example.patinflyasm37.Users.UserDAO
import com.example.patinflyasm37.Users.UserRepository
import kotlinx.coroutines.*

class MenuActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController= navHostFragment.navController





    }


}


