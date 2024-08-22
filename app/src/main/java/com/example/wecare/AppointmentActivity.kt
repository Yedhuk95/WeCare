package com.example.wecare

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
data class Doctor(
    val name: String="",
    val department: String="",
    val days: List<String> = (listOf(""))
)
class AppointmentActivity : AppCompatActivity(){
    private lateinit var adapter: DoctorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)
        val db= Firebase.firestore
        val doctors=db.collection("doctors")
        val doctorrecyclerview=findViewById<RecyclerView>(R.id.recyclerviewdoctor)
        doctors.get()
            .addOnSuccessListener { result ->
                val doctorsList = mutableListOf<Doctor>()
                for (document in result) {
                    val doctor = document.toObject(Doctor::class.java)
                    doctorsList.add(doctor)
                }
                val adapter = DoctorAdapter(doctorsList)
                doctorrecyclerview.adapter = adapter
                doctorrecyclerview.layoutManager=LinearLayoutManager(this)
            }
            .addOnFailureListener { exception ->
                Log.w("error", "Error getting documents.", exception)
            }
    }
}