package com.rockypatel.winzolite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val preferences = getSharedPreferences("loginflag", MODE_PRIVATE)
            val flag=preferences.getBoolean("flag",false)
            if (flag){
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            }else{
                startActivity(Intent(this@SplashActivity,Login::class.java))
            }

            finish()

        },3000)

    }
}