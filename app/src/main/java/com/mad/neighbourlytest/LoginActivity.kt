package com.mad.neighbourlytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logBtnMain.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }
        binding.forgetPw.setOnClickListener {
            startActivity(Intent(this,ResetPasswordActivity::class.java))
        }
    }
}