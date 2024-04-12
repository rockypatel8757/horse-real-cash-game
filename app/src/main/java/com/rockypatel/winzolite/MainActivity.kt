package com.rockypatel.winzolite

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rockypatel.winzolite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b:ActivityMainBinding
    private lateinit var mobile : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        fetchimg()
        setOnClick()
        amountwork()

        fetchCash()



    }



    private fun fetchCash() {
        val preferences = getSharedPreferences("mobilenumber", MODE_PRIVATE)

        mobile = preferences.getString("mobile", "not found").toString()
        val userPhoneNumber = mobile
        b.lyt2.text= mobile
        val database = FirebaseDatabase.getInstance()
        val userRef = database.reference.child("users").child(userPhoneNumber)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val userPoints = dataSnapshot.child("points").getValue(String::class.java)
                    b.amountDisplatBtn.text = "₹ "+ userPoints.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle any errors that occur
            }
        })
    }

    private fun amountwork() {
        b.m2.setOnClickListener {
            it.setBackgroundResource(R.drawable.amountafterclick)
            b.m5.setBackgroundResource(R.drawable.spinner_wall)
            b.m20.setBackgroundResource(R.drawable.spinner_wall)
            b.m50.setBackgroundResource(R.drawable.spinner_wall)
        }
        b.m5.setOnClickListener {
            it.setBackgroundResource(R.drawable.amountafterclick)
            b.m2.setBackgroundResource(R.drawable.spinner_wall)
            b.m20.setBackgroundResource(R.drawable.spinner_wall)
            b.m50.setBackgroundResource(R.drawable.spinner_wall)
        }
        b.m20.setOnClickListener {
            it.setBackgroundResource(R.drawable.amountafterclick)
            b.m5.setBackgroundResource(R.drawable.spinner_wall)
            b.m2.setBackgroundResource(R.drawable.spinner_wall)
            b.m50.setBackgroundResource(R.drawable.spinner_wall)
        }
        b.m50.setOnClickListener {
            it.setBackgroundResource(R.drawable.amountafterclick)
            b.m5.setBackgroundResource(R.drawable.spinner_wall)
            b.m2.setBackgroundResource(R.drawable.spinner_wall)
            b.m20.setBackgroundResource(R.drawable.spinner_wall)
        }
    }

    private fun setOnClick() {
        b.termsConditions.setOnClickListener {
            startActivity(Intent(this,TermsConditions::class.java))
        }
        b.adbutton.setOnClickListener {
            startActivity(Intent(this,PayQr::class.java))
        }

        //animation
        val manimation = AnimationUtils.loadAnimation(this, R.anim.popshow)
        b.adbutton.startAnimation(manimation)
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        b.spinnerBtn.startAnimation(animation)

        b.addcashbtn.setOnClickListener {
            startActivity(Intent(this, AddPoints::class.java))
        }
        b.onthestart.setOnClickListener {
            b.startGame.visibility= View.GONE
            b.onthestart.visibility= View.GONE
            b.termsConditions.visibility= View.VISIBLE
            b.promoimg.visibility= View.VISIBLE
            b.m2.setBackgroundResource(R.drawable.amountafterclick)
            b.m5.setBackgroundResource(R.drawable.spinner_wall)
            b.m20.setBackgroundResource(R.drawable.spinner_wall)
            b.m50.setBackgroundResource(R.drawable.spinner_wall)
        }
        b.img1.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.onthestart.visibility= View.VISIBLE
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.nameofgame.text= "Call Break"
        }
        b.img2.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.onthestart.visibility= View.VISIBLE
            b.nameofgame.text= "Rummy"
        }
        b.img3.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.onthestart.visibility= View.VISIBLE
            b.nameofgame.text= "Color prediction"
        }
        b.img4.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.onthestart.visibility= View.VISIBLE
            b.nameofgame.text= "Aviator"
        }
        b.img5.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.onthestart.visibility= View.VISIBLE
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.nameofgame.text= "Winzo Fantasy"
        }
        b.img6.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.onthestart.visibility= View.VISIBLE
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.nameofgame.text= "Player Exchange"
        }
        b.img7.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.onthestart.visibility= View.VISIBLE
            b.nameofgame.text= "Ludo Winner"
        }
        b.img8.setOnClickListener {
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.startGame.visibility = View.VISIBLE
            b.onthestart.visibility= View.VISIBLE
            b.nameofgame.text= "Poker King"
        }
        b.img9.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.onthestart.visibility= View.VISIBLE
            b.nameofgame.text= "Carrom Clash"
        }
        b.promoimg.setOnClickListener {
            b.startGame.visibility = View.VISIBLE
            b.termsConditions.visibility= View.GONE
            b.promoimg.visibility= View.GONE
            b.onthestart.visibility= View.VISIBLE
            b.nameofgame.text= "Aviator Double"
        }
    }

    private fun qrgetter(dialog: Dialog, url: String) {
        val imageView = dialog.findViewById<ImageView>(R.id.pay_image)

        try {
            Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

            dialog.setOnDismissListener {
                //Toast.makeText(this, " Payment Canceled", Toast.LENGTH_LONG).show()
            }
            // dialog.findViewById<ProgressBar>(R.id.pgbar).visibility= View.GONE

        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    private fun fetchimg() {
        setImg(b.img1,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/game%2Fcall%20break-min.png?alt=media&token=841397bb-fa2b-4ba7-aaae-4b29bc68baf8")
        setImg(b.img2,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/game%2Frummy-min.png?alt=media&token=bf97a4c8-5752-475a-947a-59d231f655ab")
        setImg(b.img3,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/colorprediction%20(1).jpg?alt=media&token=a2d1bb19-a9e1-46f6-b002-696f9274560a")
        setImg(b.img4,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/Aviator-game%20(1)%20(1).jpg?alt=media&token=d3e8f3fc-24d3-4247-9535-4127d1fe7ade")
        setImg(b.img5,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/game%2Fwinzofantasy-min.jpg?alt=media&token=485c2777-415b-4e02-96fa-67e39dae2bf3")
        setImg(b.img6,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/game%2FUntitled%20design-min.png?alt=media&token=a8d6b4d6-7902-467c-8acb-49a400be825d")
        setImg(b.img7,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/game%2Fludo-min.png?alt=media&token=d458aeac-6766-4fc1-a892-c5c23f182deb")
        setImg(b.img8,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/game%2FPOKER-min.jpg?alt=media&token=b01c4ca6-a043-4cae-8e7e-ae54f714262e")
        setImg(b.img9,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/game%2Fcarromclash-min.png?alt=media&token=80b6a337-9417-447f-9ff9-9009daa53d45")
        setImg(b.promoimg,"https://firebasestorage.googleapis.com/v0/b/winzo-lite-bfe27.appspot.com/o/game%2Faviatorbi.jpeg?alt=media&token=db6179cf-0e42-410f-ac78-30748c593396")
    }

    fun setImg(button: ImageView, url:String){

        try {
            Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(button)
        }catch (e:Exception){
            Toast.makeText(this, "error= ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun spinnerIntent(view: View) {
        startActivity(Intent(this,Game1::class.java))
    }
    fun addcashIntent(view: View) {
        startActivity(Intent(this, AddPoints::class.java))
    }
}