package com.example.wecare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth= Firebase.auth
        val user=auth.currentUser
        val db=Firebase.firestore
        if(user!=null){

        }//checking if a user is already logged in
        val signupbtn=findViewById<Button>(R.id.signup_button)

        signupbtn.setOnClickListener {
            val email=findViewById<EditText>(R.id.signup_email).text.toString()
            val password=findViewById<EditText>(R.id.signup_password).text.toString()
            val confirm_password=findViewById<EditText>(R.id.signup_confpass).text.toString()
            val name=findViewById<EditText>(R.id.name).text.toString()
            val tandc=findViewById<CheckBox>(R.id.tandc_checkbox)

            if(password==confirm_password && tandc.isChecked) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user= hashMapOf(
                                "email" to email,
                                "name" to name
                            )
                            db.collection("users")
                                .add(user)
                                .addOnSuccessListener { documentReference ->
                                    Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
                                }
                                .addOnFailureListener { e ->
                                    Log.w("Fail", "Error adding document", e)
                                }
                            Toast.makeText(
                                baseContext,
                                "Successfully Registered",
                                Toast.LENGTH_SHORT,
                            ).show()
                            val newuser = auth.currentUser
                            val intent= Intent(this,DashActivity::class.java).putExtra("user",newuser)
                            startActivity(intent)

                        } else {
                            Toast.makeText(
                                baseContext,
                                task.exception?.message.toString(),
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Password does not match/ Accept Terms and Conditions",Toast.LENGTH_SHORT).show()
            }
        }



    }
}