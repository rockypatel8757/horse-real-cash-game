package com.rockypatel.winzolite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class PayQr : AppCompatActivity() {
    private lateinit var textViewTimer: TextView
    private lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_qr)
        //countedown
        counter()





    }

    private fun counter() {
        textViewTimer = findViewById(R.id.timer)

        // Set up countdown timer
        val twoMinutesInMillis = 840000L
        countDownTimer = object : CountDownTimer(twoMinutesInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                textViewTimer.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                textViewTimer.text = "00:00"
                // Timer finished, perform actions if needed
            }
        }

        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}