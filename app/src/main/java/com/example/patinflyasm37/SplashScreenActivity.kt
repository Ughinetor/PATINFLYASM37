package com.example.patinflyasm37

import androidx.room.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.patinflyasm37.Tutorial.TutorialActivity
import com.example.patinflyasm37.Users.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val user= User("Ugo","Figus","mail@gmail.com","1234","Ciao","Italy",0.0)

        val userDao= UserDatabase.getInstance(this).userDataBaseDao()
        val userRep= UserRepository(userDao)
        userRep.insertUser(user)





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