package com.mad.neighbourlytest.activites.dinidu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.mad.neighbourlytest.R
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.models.FamilyModel


class AddFamilyActivity : AppCompatActivity() {



    private lateinit var famName:EditText
    private lateinit var famMembers:EditText
    private lateinit var famAddress:EditText
    private lateinit var contactNo:EditText
    private lateinit var famJob:EditText
    private lateinit var add:Button
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_family)



        famName=findViewById(R.id.famName)
        famMembers=findViewById(R.id.famNoOfMembers)
        famAddress=findViewById(R.id.famAddress)
        contactNo =findViewById(R.id.famContact)
        famJob =findViewById(R.id.famJob)
        add= findViewById(R.id.addFamBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Family")

        add.setOnClickListener{

            saveFamily()
        }
    }

    private fun saveFamily(){

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val email2 = sharedPreferences.getString("email", "").toString()

        val familyName = famName.text.toString()
        val noMembers = famMembers.text.toString()
        val familyAddress =famAddress.text.toString()
        val familyConNumber = contactNo.text.toString()
        val familyJob = famJob.text.toString()


        if(familyName.isEmpty()){

            famName.error ="Please Enter Name"
        }
        if(noMembers.isEmpty()){

            famMembers.error ="Please Enter Number of Family Members"

        }
        if(familyAddress.isEmpty()){

            famAddress.error ="Please Enter Family Address"
        }
        if(familyConNumber.isEmpty()){

            contactNo.error="Please Enter Contact Number"

        }
        if(familyJob.isEmpty()){

            famJob.error="Please Enter Job"
        }

        val  familyID =dbRef.push().key!!
        val family = FamilyModel(familyID,familyName,noMembers,familyAddress,familyConNumber,familyJob,email2)

        dbRef.child(familyID).setValue(family)
            .addOnSuccessListener{
                Toast.makeText(this,"Data Inserted Successfully",Toast.LENGTH_LONG).show()
                famName.text.clear()
                famMembers.text.clear()
                famAddress.text.clear()
                contactNo.text.clear()
                famJob.text.clear()

            }
            .addOnFailureListener{ err ->
                Toast.makeText(this,"Data failed ${err.message}",Toast.LENGTH_LONG).show()
            }

    }
}