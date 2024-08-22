package com.example.wecare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class DashActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val email=intent.getStringExtra("user")
        if (email != null) {
            findUserName(email){username->
                findViewById<TextView>(R.id.welcometext).text=username+"!"
            }
        }
        setContentView(R.layout.activity_welcome)
        findViewById<Button>(R.id.nutrition_button).setOnClickListener {
            val intent=Intent(this,NutritionActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.fitness_button).setOnClickListener {
            val intent=Intent(this,FitnessActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.vaccine_button).setOnClickListener {
            val intent=Intent(this,VaccinationActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.chat_button).setOnClickListener {
            val intent=Intent(this,ChatForumsActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.appointment_button).setOnClickListener {
            val intent=Intent(this,AppointmentActivity::class.java)
            startActivity(intent)
        }


    }


    private fun findUserName(email:String, callback: (String?)->Unit)
    {
        val db = Firebase.firestore
        var username=""
        db.collection("users").whereEqualTo("email",email)
            .get()
            .addOnSuccessListener{
                    querySnapshot->
                if(!querySnapshot.isEmpty)
                {
                    val userDoc=querySnapshot.documents[0].data
                    Log.d("Snapshot",userDoc.toString())
                    if (userDoc != null)
                    {
                        username=userDoc.get("name").toString()
                        callback(username)
                    }
                    else
                    {
                        callback(null)
                    }
                }
            }
    }
}