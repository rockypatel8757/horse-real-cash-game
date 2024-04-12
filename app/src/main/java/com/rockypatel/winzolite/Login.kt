package com.rockypatel.winzolite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.FirebaseDatabase
import com.rockypatel.winzolite.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var b:ActivityLoginBinding
    private lateinit var mobile:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)


        val mobileNumber =b.loginMobile
        val lg=findViewById<Button>(R.id.loginBtn)
        lg.setOnClickListener {
            mobile= mobileNumber.text.toString()
            //sendNumber()
            val database = FirebaseDatabase.getInstance()
            val userRef = database.reference.child("users").child(mobile)
            userRef.child("points").setValue("00")


            val pref= getSharedPreferences("mobilenumber", MODE_PRIVATE)
            val prefmob=pref.edit()
            prefmob.putString("mobile",mobile)
            prefmob.apply()
            val preferences= getSharedPreferences("loginflag", MODE_PRIVATE)
            val editPref= preferences.edit()
            editPref.putBoolean("flag",true)
            editPref.apply()
            if (mobile.isEmpty()){
                b.loginMobile.error
            }else{
                startActivity(Intent(this@Login, MainActivity::class.java))
                finish()
            }

        }




    }
}