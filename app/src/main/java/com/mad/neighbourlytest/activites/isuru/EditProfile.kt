package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mad.neighbourlytest.databinding.ActivityEditProfileBinding
import cn.pedant.SweetAlert.SweetAlertDialog;

class EditProfile : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        //making a sharedPreference to access in the app
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val name2 = sharedPreferences.getString("name", "").toString()
        val email2 = sharedPreferences.getString("email", "").toString()
        val mobile2 = sharedPreferences.getString("mobile", "").toString()
        val id2 = sharedPreferences.getString("id", "").toString()
        val type2 = sharedPreferences.getString("type", "").toString()

        //setting the values of the layout
        binding.editMobile.setText(mobile2)
        binding.editEmail.setText(email2)
        binding.editNIC.setText(id2)
        binding.editName.setText(name2)
        binding.editType.setText(type2)

        progressBar = binding.progressBar

        binding.deleteBtn.setOnClickListener {
            val email = binding.editEmail.text.toString()

            deletedata(email)
        }

        binding.saveBtn.setOnClickListener {
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val mobile = binding.editMobile.text.toString()
            val type = binding.editType.text.toString()
            val nic = binding.editNIC.text.toString()

            updatedata(name, email, mobile, type, nic)
        }

        binding.menuHome.setOnClickListener {
            //making a sharedPreference to access in the app
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val type2 = sharedPreferences.getString("type", "").toString()
            if (type2 == "Donor") {
                startActivity(Intent(this, Menu2::class.java))
            } else {
                startActivity(Intent(this, Menu::class.java))
            }
        }
        binding.menuHome2.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }

    private fun updatedata(name:String, email:String, mobile:String, type:String, nic:String){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("USERS").document(email)

        val newData = hashMapOf(
            "name" to name,
            "email" to email,
            "mobile" to mobile,
            "id" to nic,
            "type" to type,
        )

        // Show confirmation dialog before updating data
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Confirm Update")
            .setContentText("Are you sure you want to update this data?")
            .setConfirmText("Update")
            .setConfirmClickListener { sDialog ->
                sDialog.dismissWithAnimation()

                // Show progress bar
                progressBar.visibility = View.VISIBLE

                // Update data in Firestore
                docRef.update(newData as Map<String, Any>).addOnCompleteListener { task ->
                    // Hide progress bar
                    progressBar.visibility = View.GONE

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Data updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Update values in shared preferences
                        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("name", name)
                        editor.putString("email", email)
                        editor.putString("mobile", mobile)
                        editor.putString("type", type)
                        editor.putString("id", nic)
                        editor.apply()
                    } else {
                        Toast.makeText(
                            this,
                            "Error updating data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .setCancelButton("Cancel") { sDialog -> sDialog.dismissWithAnimation() }
            .show()
    }

    private fun deletedata(email:String){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("USERS").document(email)

        // Show confirmation dialog before deleting data
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Confirm Delete")
            .setContentText("Are you sure you want to delete this data?")
            .setConfirmText("Delete")
            .setConfirmClickListener { sDialog ->
                sDialog.dismissWithAnimation()

                // Show progress bar
                progressBar.visibility = View.VISIBLE

                // Delete data from Firestore
                docRef.delete().addOnCompleteListener { task ->
                    // Hide progress bar
                    progressBar.visibility = View.GONE

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Data deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Clear shared preferences
                        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.clear()
                        editor.apply()

                        // Go back to login activity
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Error deleting data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .setCancelButton("Cancel") { sDialog -> sDialog.dismissWithAnimation() }
            .show()
    }

}