package com.mad.neighbourlytest.activites.isuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mad.neighbourlytest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var loginProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        loginProgressBar = binding.progressBar

        //user login process
        binding.logBtnMain.setOnClickListener {
            val email = binding.logEmail.text.toString().trim()
            val password = binding.logPwd.text.toString().trim()

            //validations
            if (email.isEmpty() || email == " ") {
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!email.contains('@')) {
                Toast.makeText(this, "Enter a valid Email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty() || password == " ") {
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 8) {
                Toast.makeText(this, "Password Must be 8 Characters long", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                binding.progressBar.visibility = View.VISIBLE // show progress bar
                loginUser(email, password)
            }
        }
        binding.forgetPw.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
        binding.regLogBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        // show the progress bar
        loginProgressBar.visibility = View.VISIBLE

        //adding user to the authentication
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { login ->
            // hide the progress bar
            loginProgressBar.visibility = View.GONE

            if (login.isSuccessful) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, login.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
