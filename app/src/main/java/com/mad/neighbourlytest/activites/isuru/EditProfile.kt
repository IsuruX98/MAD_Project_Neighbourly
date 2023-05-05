package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mad.neighbourlytest.databinding.ActivityEditProfileBinding
import cn.pedant.SweetAlert.SweetAlertDialog;
class EditProfile : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

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

        //setting thr values of the layout
        binding.editMobile.setText(mobile2)
        binding.editEmail.setText(email2)
        binding.editNIC.setText(id2)
        binding.editName.setText(name2)
        binding.editType.setText(type2)


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

            updatedata(name,email,mobile,type,nic)
        }

        binding.menuHome.setOnClickListener {
            //making a sharedPreference to access in the app
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val type2 = sharedPreferences.getString("type", "").toString()
            if(type2=="Donor"){
                startActivity(Intent(this, Menu2::class.java))
            }else{
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

                // Update data in Firestore
                docRef.update(newData as Map<String, Any>).addOnSuccessListener {
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener{
                    Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()
                }
            }
            .setCancelText("Cancel")
            .setCancelClickListener { sDialog ->
                sDialog.dismissWithAnimation()
            }
            .show()
    }
    private fun deletedata(email:String){
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("USERS")
        val documentRef = collectionRef.document(email)

        // Show confirmation dialog before deleting data
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Delete")
            .setContentText("Are you sure you want to delete your account?")
            .setConfirmText("Yes, delete it")
            .setConfirmClickListener { sDialog ->
                sDialog.dismissWithAnimation()
                documentRef.delete().addOnSuccessListener {
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                    // Log out user
                    FirebaseAuth.getInstance().signOut()
                    // Redirect to LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener{
                    Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
                }
            }
            .setCancelText("No")
            .setCancelClickListener { sDialog -> sDialog.dismissWithAnimation() }
            .show()
    }

}