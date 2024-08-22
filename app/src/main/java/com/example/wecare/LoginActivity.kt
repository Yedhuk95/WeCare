package com.example.wecare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth= Firebase.auth

        val loginbutton=findViewById<Button>(R.id.signin_button)
        val signupbutton=findViewById<Button>(R.id.signup_signin)
        signupbutton.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        loginbutton.setOnClickListener {
            val email=findViewById<EditText>(R.id.signup_email).text.toString()
            val password=findViewById<EditText>(R.id.signup_password).text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        var name=""
                        user?.let{
                            name=it.email.toString()
                        }

                        val intent=Intent(this,DashActivity::class.java).putExtra("user",name)
                        startActivity(intent)
                    } else {
                        Log.w("failedsignin", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Check Email/Password",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }
    }

}