package com.example.patinflyasm37.logpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.patinflyasm37.R

class SignLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sign_log)

            // Load the login fragment initially
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpFragment())
                .commit()
        }
    }


