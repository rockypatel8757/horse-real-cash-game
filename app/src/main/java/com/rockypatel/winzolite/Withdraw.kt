package com.rockypatel.winzolite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rockypatel.winzolite.databinding.ActivityWithdrawBinding

class Withdraw : AppCompatActivity() {
    private lateinit var b:ActivityWithdrawBinding
    private lateinit var mobile : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityWithdrawBinding.inflate(layoutInflater)
        setContentView(b.root)
        var myPoints="00"
        val preferences = getSharedPreferences("mobilenumber", MODE_PRIVATE)
        mobile = preferences.getString("mobile", "not found").toString()
        val userPhoneNumber = mobile
        val database = FirebaseDatabase.getInstance()
        val userRef = database.reference.child("users").child(userPhoneNumber)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val userPoints = dataSnapshot.child("points").getValue(String::class.java)
                    myPoints=userPoints.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


        val amountM = b.amountEditText
        val eupi= b.upiEditText
        b.submitmoney.setOnClickListener {
            val points= myPoints.toIntOrNull()
            val amount= amountM.text
            val upi= eupi.text
            if (points != null)
            if (points > 0)
            if (!amount.isNullOrEmpty() && !upi.isNullOrEmpty()){

                Toast.makeText(this, "Amount Has been requested, it will be paid in 24 hours", Toast.LENGTH_LONG).show()
            }else{
               amountM.error = "first fill this"
               eupi.error = "first fill this"
            }
            else
                Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show()

        }


    }
}