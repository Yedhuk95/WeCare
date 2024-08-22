package com.example.wecare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(private val doctors: List<Doctor>) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDoctorName: TextView = itemView.findViewById(R.id.textViewDoctorName)
        val textViewDoctorDepartment: TextView=itemView.findViewById(R.id.textViewDoctorDepartment)
        val textViewDoctorAvailableDays: TextView=itemView.findViewById(R.id.textViewDoctorAvailableDays)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.textViewDoctorName.text ="Name: Dr."+doctor.name
        holder.textViewDoctorDepartment.text="Department: "+doctor.department
        var days="Days Available: "
        for(day in doctor.days)
            days+=day+" "
        holder.textViewDoctorAvailableDays.text=days


        // Bind more data if needed
    }

    override fun getItemCount(): Int {
        return doctors.size
    }
}