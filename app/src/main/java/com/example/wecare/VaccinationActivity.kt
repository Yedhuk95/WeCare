package com.example.wecare

import MyAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class VaccinationActivity:AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccination)
        val vacrecyclerView = findViewById<RecyclerView>(R.id.recyclerviewvaccine)
        findViewById<Button>(R.id.vac_submit).setOnClickListener {
            val dob=findViewById<EditText>(R.id.age_day).text.toString().toInt()
            val vaccineDescriptions = arrayOf(
                "DTwP /DTaP1, Hib-1, IPV-1, Hep B2, PCV 1,Rota-1",
                "DTwP /DTaP2, Hib-2, IPV-2, Hep B3, PCV 2, Rota-2",
                "DTwP /DTaP3, Hib-3, IPV-3, Hep B4, PCV 3, Rota-3*",
                "Influenza-1", "Influenza-2",
                "Typhoid Conjugate Vaccine",
                "MMR 1 (Mumps, measles, Rubella)",
                "Hepatitis A- 1",
                "PCV Booster", "MMR 2, Varicella",
                "DTwP /DTaP, Hib, IPV", "Hepatitis A- 2**, Varicella 2",
                "DTwP /DTaP, IPV, MMR 3", "HPV (2 doses)", "Tdap/ Td"
            )
            val daysRemaining = intArrayOf(42-dob, 70-dob, 90-dob, 183-dob, 213-dob, 228-dob, 274-dob, 365-dob, 410-dob, 456-dob, 518-dob, 548-dob, 1826-dob, 4382-dob, 4017-dob)

            adapter = MyAdapter(vaccineDescriptions, daysRemaining)
            vacrecyclerView.adapter = adapter
            vacrecyclerView.layoutManager = LinearLayoutManager(this)
        }


    }

}