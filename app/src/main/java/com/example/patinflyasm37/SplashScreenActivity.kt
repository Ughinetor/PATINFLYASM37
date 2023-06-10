package com.example.patinflyasm37

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.patinflyasm37.Data.*
import com.example.patinflyasm37.Tutorial.TutorialActivity
import com.example.patinflyasm37.Internet.CustomJsonObjectRequest
import com.example.patinflyasm37.Internet.GenericErrorListener
import com.example.patinflyasm37.Internet.GenericGetSuccessListener
import com.google.gson.Gson
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val user= User("Ugo","Figus","mail@gmail.com","1234","Ciao","Italy",0.0)
        val userDao= AppDatabase.getInstance(this).userDao()
        val scooterDao= AppDatabase.getInstance(this).scooterDao()
        databaseCleanInsertWithCoroutines(this,userDao,user)
        supportActionBar?.hide()
        val backgroundImg : ImageView = findViewById(R.id.iv_logo)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.animation)
        backgroundImg.startAnimation(sideAnimation)
        lifecycleScope.launch {
            makeHTTPRequest(this@SplashScreenActivity, scooterDao)
            delay(3000)
            startActivity(Intent(this@SplashScreenActivity, TutorialActivity::class.java))
            finish()
        }

    }




    fun makeHTTPRequest(context: Context,scooterDao: ScooterDao) {
        // Instantiate the RequestQueue. The queue is unique for all the requests
        val queue = Volley.newRequestQueue(this)
        var url = "https://www.google.com"
        // Generic error response
        val genericErrorListener= GenericErrorListener()
        val genericGetSuccessListener = GenericGetSuccessListener()

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            genericGetSuccessListener,genericErrorListener
        )
        url = "https://patinfly.com/endpoints/scooter"
        val api_key: String = "1imVXNFY6Qvh5O3bCjnL94qpgat3dc0JyZ7IGD35"


        var jsonResponse: String? = null

        val jsonObjectRequest = CustomJsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                scooterInsertWithCoroutines(this,scooterDao,response.toString())
            },
            genericErrorListener,
            api_key
        )
        // Add the string the request to the RequestQueue
        queue.add(stringRequest)
        // Add the json the request to the RequestQueue
        queue.add(jsonObjectRequest)





    }

    private fun scooterInsertWithCoroutines(context: Context, scooterDao: ScooterDao, resource : String) {
        CoroutineScope(Dispatchers.Default).launch {
            val insertResult: Deferred<Any> = ScooterRepository.insertScooters(context,scooterDao,resource)
            insertResult.await()
        }

    }


}
fun databaseCleanInsertWithCoroutines(context: Context, userDao: UserDAO, user : User){
    CoroutineScope(Dispatchers.Default).launch {
        val insertResult: Deferred<Any> = UserRepository().insertUser(context, userDao,user)
        insertResult.await()

    }
}

