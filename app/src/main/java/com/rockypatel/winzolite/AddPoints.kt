package com.rockypatel.winzolite

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.rockypatel.winzolite.databinding.ActivityAddPointsBinding

class AddPoints : AppCompatActivity() {
    private lateinit var b: ActivityAddPointsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityAddPointsBinding.inflate(layoutInflater)
        setContentView(b.root)

        onclick()

        val animation = AnimationUtils.loadAnimation(this, R.anim.popshow)
        b.adshower.startAnimation(animation)

    }

    private fun onclick() {


        b.withdrawcashBtn.setOnClickListener {
            startActivity(Intent(this, Withdraw::class.java))
        }
        b.pay50btn.setOnClickListener {
            val intent = Intent(this, PayQr::class.java)
            intent.putExtra("amount","50")
            startActivity(intent)
        }
        b.pay100btn.setOnClickListener {

            val intent = Intent(this, PayQr::class.java)
            intent.putExtra("amount","100")
            startActivity(intent)
        }
        b.pay200btn.setOnClickListener {
            val intent = Intent(this, PayQr::class.java)
            intent.putExtra("amount","200")
            startActivity(intent)
        }
        b.pay500btn.setOnClickListener {
            val intent = Intent(this, PayQr::class.java)
            intent.putExtra("amount","500")
            startActivity(intent)
        }

        b.adshower.setOnClickListener {
            val intent = Intent(this, PayQr::class.java)
            intent.putExtra("amount","50")
            startActivity(intent)
        }

    }


}

