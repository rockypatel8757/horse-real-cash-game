package com.rockypatel.winzolite

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rockypatel.winzolite.databinding.ActivityGame1Binding

class Game1 : AppCompatActivity() {
    private lateinit var b: ActivityGame1Binding
    private lateinit var mobile : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityGame1Binding.inflate(layoutInflater)
        setContentView(b.root)

        val spinner = b.spinner
        val initialRotation = spinner.rotation
        var myPoints = "00"
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
                // Handle any errors that occur
            }
        })


        b.play1.setOnClickListener {
            val mpoints = myPoints.toIntOrNull()
            val luckDialog = Dialog(this@Game1)
            luckDialog.setContentView(R.layout.luck)


            if (mpoints != null)
                if (mpoints > 0){
                    ObjectAnimator.ofFloat(spinner, "rotation", 0f, 1460f).apply {
                        duration = 5000
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                // Set the final rotation value after the animation completes
                                spinner.rotation = initialRotation + 1460f
                                userRef.child("points").setValue("00")
                                    .addOnSuccessListener {
                                        // Points updated successfully
                                        // Show the luck dialog or perform any other actions here
                                        luckDialog.show()
                                    }
                                    .addOnFailureListener { e ->
                                        // Failed to update points
                                        // Handle the failure, log, or show an error message
                                    }
                                luckDialog.show()
                            }
                        })
                        start()



                    }

                }else{
                    val dialogadd = Dialog(this)
                    dialogadd.setContentView(R.layout.add_points_pop)
                    dialogadd.findViewById<AppCompatButton>(R.id.addpointspop).setOnClickListener {
                        dialogadd.dismiss()
                        startActivity(Intent(this,AddPoints::class.java))

                    }
                   dialogadd.show()
                }







        }



    }
}