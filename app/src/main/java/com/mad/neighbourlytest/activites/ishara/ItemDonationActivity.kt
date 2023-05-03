package com.mad.neighbourlytest.activites.ishara


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.R
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.activites.isuru.Menu
import com.mad.neighbourlytest.activites.isuru.Menu2
import com.mad.neighbourlytest.models.ItemDonationModel

class ItemDonationActivity : AppCompatActivity() {

    //declare variables for donation request
    private lateinit var typeDonation : EditText
    private lateinit var quantityDonation : EditText
    private lateinit var expireDonation :  EditText
    private lateinit var contactNameDonation : EditText
    private lateinit var contactNumberDonation : EditText
    private lateinit var addBtn : Button
    private lateinit var homeBtn : Button

    //database references
    private lateinit var dataBase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_request)

        //initialize variables
        typeDonation = findViewById(R.id.DBulkType)
        quantityDonation = findViewById(R.id.DBulkQuantity)
        expireDonation = findViewById(R.id.DBulkExDate)
        contactNameDonation = findViewById(R.id.DBulkContactPersonName)
        contactNumberDonation = findViewById(R.id.DBulkContactPersonMobile)
        addBtn = findViewById(R.id.addDBulkBtn)


        dataBase = FirebaseDatabase.getInstance().getReference("Donation Items")

        //onclick listner for add button
        addBtn.setOnClickListener {
            saveDonation()
        }

//        binding.menuHome.setOnClickListener {
//            //making a sharedPreference to access in the app
//            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//            val type2 = sharedPreferences.getString("type", "").toString()
//            if(type2=="Donor"){
//                startActivity(Intent(this, Menu2::class.java))
//            }else{
//                startActivity(Intent(this, Menu::class.java))
//            }
//        }
//        binding.menuHome2.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//        }


    }
    //function for add button
    private fun saveDonation(){
        val type = typeDonation.text.toString()
        val qty = quantityDonation.text.toString()
        val exp = expireDonation.text.toString()
        val cName = contactNameDonation.text.toString()
        val cNum = contactNumberDonation.text.toString()

        //check null value validation
        if (type.isEmpty()) {
            SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText("Donation Type is required")
                .show()
            return
        }
        if (qty.isEmpty()) {
            SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText("Quantity of Donation is required")
                .show()
            return
        }
        if (cName.isEmpty()) {
            SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText("Name of the Donor is required")
                .show()
            return
        }
        if (cNum.isEmpty()) {
            SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText("Phone Number of the Donor is required")
                .show()
            return
        }
        if(cNum.length != 10){
            SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText("Phone Number Must be 10 digits")
                .show()
            return

        }


        //create donation id using database push method
           val donationID = dataBase.push().key!!
           //connect model
           val itemDonation = ItemDonationModel(donationID,type,qty,exp,cName,cNum)


           dataBase.child(donationID).setValue(itemDonation)
               .addOnCompleteListener{
                   //clear input fields
                   typeDonation.text.clear()
                   quantityDonation.text.clear()
                   expireDonation.text.clear()
                   contactNameDonation.text.clear()
                   contactNumberDonation.text.clear()

                   val intent = Intent(this, ThankYouActivity::class.java)
                   startActivity(intent)

               }.addOnFailureListener{
                       err -> SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                   .setTitleText("Error")
                   .setContentText(err.message)
                   .show()
               }



    }
}