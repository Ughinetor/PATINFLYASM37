package com.example.patinflyasm37.logpage

import LoginFragment
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.fragment.app.Fragment
import com.example.patinflyasm37.MenuActivity
import com.example.patinflyasm37.R
import com.example.patinflyasm37.Users.AppDatabase
import com.example.patinflyasm37.Users.User
import com.example.patinflyasm37.databaseCleanInsertWithCoroutines

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userDao = AppDatabase.getInstance(requireContext()).userDao()


        val rootView = inflater.inflate(R.layout.activity_sign_up, container, false)
        val name = rootView.findViewById<EditText>(R.id.name)
        val surname=rootView.findViewById<EditText>(R.id.surname)
        val email=rootView.findViewById<EditText>(R.id.email)
        val phone= rootView.findViewById<EditText>(R.id.phone1)
        val passwordEditText = rootView.findViewById<EditText>(R.id.password)
        val confirmPasswordEditText = rootView.findViewById<EditText>(R.id.repassword)
        val registerButton = rootView.findViewById<Button>(R.id.signupbtn)
        val loginbutton= rootView.findViewById<Button>(R.id.loginbtn)
        var nation: String?= null

        val mSpinner = rootView.findViewById<Spinner>(R.id.nationality_spinner)
        val nations = listOf(
            "Germany",
            "United Kingdom",
            "France",
            "Italy",
            "Spain",
            "Russia",
            "Netherlands"
        )

        val mArrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_theme, nations)
        mArrayAdapter.setDropDownViewResource(R.layout.spinner_theme)
        mSpinner.adapter = mArrayAdapter
        mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nation = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        val passwordTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = passwordEditText.text.toString()
                val confirmPassword = confirmPasswordEditText.text.toString()
                val passwordsMatch = (password == confirmPassword) and email.text.toString().isNotEmpty()
                registerButton.isEnabled = passwordsMatch
            }
            override fun afterTextChanged(s: Editable?) {
            }
        }

        passwordEditText.addTextChangedListener(passwordTextWatcher)
        confirmPasswordEditText.addTextChangedListener(passwordTextWatcher)


        registerButton.setOnClickListener {
            var user= User(name.text.toString(),surname.text.toString(),email.text.toString(),phone.text.toString(),passwordEditText.text.toString(),nation!!,0.0)
            databaseCleanInsertWithCoroutines(requireContext(),userDao,user)
            val intent = Intent(requireContext(), MenuActivity::class.java)
            intent.putExtra("mail", user.email)
            startActivity(intent)
            requireActivity().finish()
        }


        loginbutton.setOnClickListener {
            val loginFragment = LoginFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, loginFragment)
                .addToBackStack(null) // Optional: Add the transaction to the back stack
                .commit()

        }


        return rootView    }




}
