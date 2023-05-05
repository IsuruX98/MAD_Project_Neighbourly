package com.mad.neighbourlytest.activites.isuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.resetPwBtn.setOnClickListener {
            val email = binding.resetEmail.text.toString().trim()

            //validation
            if (email.isEmpty() || email == " ") {
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!email.contains('@')) {
                Toast.makeText(this, "Enter a valid Email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                //reset password process
                auth.sendPasswordResetEmail(email).addOnCompleteListener(this){ reset->
                    if (reset.isSuccessful){
                        Toast.makeText(this, "Check your email for password reset", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this, reset.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}