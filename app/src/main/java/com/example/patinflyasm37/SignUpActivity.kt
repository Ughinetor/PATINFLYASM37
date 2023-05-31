package com.example.patinflyasm37

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Declaring and initializing the Spinner from the layout file
        val mSpinner = findViewById<Spinner>(R.id.nationality_spinner)

        val nations = listOf(
            "Germany",
            "United Kingdom",
            "France",
            "Italy",
            "Spain",
            "Russia",
            "Netherlands"
        )

        // Create an adapter as shown below
        val mArrayAdapter = ArrayAdapter<Any?>(this, R.layout.spinner_theme,nations  )
        mArrayAdapter.setDropDownViewResource(R.layout.spinner_theme)

        // Set the adapter to the Spinner
        mSpinner.adapter = mArrayAdapter





    }
}