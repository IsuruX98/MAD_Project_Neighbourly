package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mad.neighbourlytest.databinding.ActivityProfileBinding
import cn.pedant.SweetAlert.SweetAlertDialog;

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        //making a sharedPreference to access in the app
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val name2 = sharedPreferences.getString("name", "").toString()
        val email2 = sharedPreferences.getString("email", "").toString()
        val mobile2 = sharedPreferences.getString("mobile", "").toString()
        val id2 = sharedPreferences.getString("id", "").toString()
        val type2 = sharedPreferences.getString("type", "").toString()

        binding.pdContactNo.setText(mobile2)
        binding.pdEmail.setText(email2)
        binding.pdNic.setText(id2)
        binding.pdName.text = name2
        binding.pdType.text = type2

        binding.editBtn.setOnClickListener {
            startActivity(Intent(this, EditProfile::class.java))
        }
        binding.btnLogout.setOnClickListener {
            val sweetAlertDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure you want to log out?")
                .setConfirmButton("Yes") { sDialog ->
                    sDialog.dismissWithAnimation()
                    auth.signOut()
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setCancelButton("No") { sDialog ->
                    sDialog.dismissWithAnimation()
                }
            sweetAlertDialog.show()
        }
    }
}