package com.example.patinflyasm37

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.patinflyasm37.Tutorial.TutorialActivity
import com.example.patinflyasm37.Users.*
import com.example.patinflyasm37.Users.AppDatabase
import com.example.patinflyasm37.Users.User
import com.example.patinflyasm37.Users.UserDAO
import com.example.patinflyasm37.Users.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val user= User("Ugo","Figus","mail@gmail.com","1234","Ciao","Italy",0.0)
        val userDao= AppDatabase.getInstance(this).userDao()
        databaseCleanInsertWithCoroutines(this,userDao,user)
        supportActionBar?.hide()
        val backgroundImg : ImageView = findViewById(R.id.iv_logo)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.animation)
        backgroundImg.startAnimation(sideAnimation)
        Handler().postDelayed({
            startActivity(Intent(this, TutorialActivity::class.java))
            finish()
        },3000)

    }

}
fun databaseCleanInsertWithCoroutines(context: Context, userDao: UserDAO, user : User){
    CoroutineScope(Dispatchers.Default).launch {

        val insertResult: Deferred<Any> = UserRepository().insertUser(context, userDao,user)
        insertResult.await()

    }
}



