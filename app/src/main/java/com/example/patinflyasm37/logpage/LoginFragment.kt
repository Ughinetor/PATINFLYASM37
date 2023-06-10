

import android.annotation.SuppressLint
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
import com.example.patinflyasm37.Data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment: Fragment() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userDao = AppDatabase.getInstance(requireContext()).userDao()

        val rootView = inflater.inflate(R.layout.loginfrag, container, false)

        val email=rootView.findViewById<EditText>(R.id.email)

        val passwordEditText = rootView.findViewById<EditText>(R.id.password)


        val loginbutton= rootView.findViewById<Button>(R.id.loginbtn)

        val passwordTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = passwordEditText.text.toString()

                val passwordsMatch = password.isNotEmpty() and email.text.toString().isNotEmpty()
                loginbutton.isEnabled = passwordsMatch
            }
            override fun afterTextChanged(s: Editable?) {
            }
        }





        loginbutton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val user = withContext(Dispatchers.IO) {
                    userDao.getUserByEmail(email.text.toString())
                }
                if (user?.password == passwordEditText.text.toString()) {
                    val intent = Intent(requireContext(), MenuActivity::class.java)
                    intent.putExtra("mail", user.email)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }


        return rootView    }

}