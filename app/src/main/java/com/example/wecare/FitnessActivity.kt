package com.example.wecare

import ExerciseAdapter
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response


data class Exercise(
    val name: String,
    val type: String,
    val muscle: String,
    val equipment: String,
    val difficulty: String,
    val instructions: String
)
interface ExerciseApiService {
    @GET("v1/exercises")
    fun getExercisesByMuscle(
        @Query("muscle") muscle: String,
        @Query("X-Api-Key") apiKey: String,
        @Query("type") type: String,
        @Query("difficulty") difficulty: String
    ): Call<List<Exercise>> // Change the response type as per your API response format
}
object RetrofitClient {
    private const val BASE_URL = "https://api.api-ninjas.com/"

    fun create(): ExerciseApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ExerciseApiService::class.java)
    }
}



class FitnessActivity : AppCompatActivity() {

    private lateinit var adapter: ExerciseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness)
        adapter = ExerciseAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewfitness)
        val exerciseTypeSpinner: Spinner = findViewById(R.id.ex_typesel)
        ArrayAdapter.createFromResource(
            this,
            R.array.exercise_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            exerciseTypeSpinner.adapter = adapter
        }

        val muscleGroupSpinner: Spinner = findViewById(R.id.ex_musclesel)
        ArrayAdapter.createFromResource(
            this,
            R.array.muscle_groups,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            muscleGroupSpinner.adapter = adapter
        }
        var exerciseType=""
        var muscleName=""
        findViewById<Button>(R.id.show_button).setOnClickListener {

            exerciseTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    exerciseType = parent?.getItemAtPosition(position).toString().lowercase()
                    // Use the selectedExerciseType as needed
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
            muscleGroupSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    muscleName = parent?.getItemAtPosition(position).toString().lowercase()
                    // Use the selectedExerciseType as needed
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
            fetchExercisesByMuscle(muscleName,exerciseType)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.set(0, 0, 0, 8) // Adjust the bottom spacing as needed
            }
        })
        recyclerView.adapter = adapter
    }
    fun fetchExercisesByMuscle(muscle: String,type: String) {
        val apiKey = "2yTsg9JeiNH5FmMZa8ftiDFTTG9nia0z9pon3jZC"
        val exerciseApiService = RetrofitClient.create()

        val call = exerciseApiService.getExercisesByMuscle(muscle, apiKey ,type,"beginner")
        call.enqueue(object : Callback<List<Exercise>> { // Change the response type as per your API response format
            override fun onResponse(call: Call<List<Exercise>>, response: Response<List<Exercise>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        adapter.clear()
                        adapter.addExercise(responseBody)
                    }
                    Log.d("Response",responseBody.toString())
                    // Handle successful response here
                } else {
                    Log.d("Error","Error: ${response.code()} - ${response.errorBody()?.string()}")
                    // Handle error response here
                }
            }

            override fun onFailure(call: Call<List<Exercise>>, t: Throwable) {
                println("Request failed: ${t.message}")
                // Handle failure here
            }


        })
    }

    }

