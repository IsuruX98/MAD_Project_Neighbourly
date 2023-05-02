package com.mad.neighbourlytest.activites.isuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mad.neighbourlytest.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.regBtn.setOnClickListener{
            val name = binding.regName.text.toString().trim();
            val email = binding.regEmail.text.toString().trim();
            val mobile = binding.regMobile.text.toString().trim();
            val id = binding.regId.text.toString().trim();
            val type = binding.regType.selectedItem.toString().trim();
            val password = binding.regPassword.text.toString().trim();
            val cpassword = binding.regPassword2.text.toString().trim();

            if (name.isEmpty() || name == " ") {
                Toast.makeText(this, "User name required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.isEmpty() || email == " ") {
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!email.contains('@')) {
                Toast.makeText(this, "Enter a valid Email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mobile.isEmpty() || mobile == " ") {
                Toast.makeText(this, "Mobile is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (id.isEmpty() || id == " ") {
                Toast.makeText(this, "ID number is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (type.isEmpty() || type == " ") {
                Toast.makeText(this, "Select the user type", Toast.LENGTH_SHORT).show()
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
            }
            if (password != cpassword) {
                Toast.makeText(this, "Password Miss match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                register(email, password, name, mobile,id,type)
            }
        }

    }
    private fun register(email: String, password: String, name: String, mobile: String,id:String,type:String) {

        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "mobile" to mobile,
            "id" to id,
            "type" to type
        )
        val Users = db.collection("USERS")
        val query = Users.whereEqualTo("email", email).get().addOnSuccessListener { tasks ->
            if (tasks.isEmpty) {

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                    if (it.isSuccessful) {

                        Users.document(email).set(user)

                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "User already registered", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}