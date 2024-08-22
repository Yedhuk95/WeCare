package com.example.wecare

import RecipeAdapter
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

data class ApiResponse(
    val hits: List<RecipeHit>
)

data class RecipeHit(
    val recipe: RecipeDetail
)

data class RecipeDetail(
    val label: String,
    val url: String,
    val image: String,
    val ingredientLines: List<String>,
    val calories: Float,
    // Add other fields as required
)
interface RecipeService {
    @GET("/search")
    suspend fun searchRecipes(
        @Query("q") query: String?,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("diet") diet: List<String>?,
       @Query("cusineType") cusineType: List<String>?,
        @Query("health") health: List<String>?,
       @Query("random") random: Boolean

    ): ApiResponse
}
object RecipeApiClient {
    private const val BASE_URL = " https://api.edamam.com/"
    private const val APP_ID = "c0de6b55"
    private const val APP_KEY = "65c2658cbdab10132ec8a03d8f3b0e06"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: RecipeService = retrofit.create(RecipeService::class.java)

    suspend fun searchRecipes(query: String,health: List<String>?): ApiResponse {
        Log.d("SearchRecipes","hit")
        return service.searchRecipes(query, APP_ID, APP_KEY, listOf("high-fiber"), listOf("Indian"), health,true)
    }
}

class NutritionActivity: AppCompatActivity() {
    private lateinit var adapter: RecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition)
        adapter = RecipeAdapter()
        val recview=findViewById<RecyclerView>(R.id.recyclerView)
        findViewById<Button>(R.id.generate_button).setOnClickListener {
            val selectedId=findViewById<RadioGroup>(R.id.dietgroup).checkedRadioButtonId
            val diet= findViewById<RadioButton>(selectedId).text.toString().lowercase()
            val querystring=findViewById<EditText>(R.id.query).text

            var dietlist= listOf("alcohol-free")
            if(diet=="vegetarian" || diet=="vegan")
                dietlist+=diet

            fetchRecipes(querystring.toString(),dietlist)
            recview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            recview.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.set(0, 0, 0, 8) // Adjust the bottom spacing as needed
                }
            })
            recview.adapter=adapter
        }

    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchRecipes(query: String,health: List<String>?) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = RecipeApiClient.searchRecipes(query,health)
                Log.d("Response", response.toString())
                withContext(Dispatchers.Main) {
                    handleResponse(response)
                }
            } catch (e: Exception) {
                // Handle error
                Log.e("error", "Error fetching recipes: ${e.message}")
            }
        }
    }

    private fun handleResponse(response: ApiResponse) {
        // Process the response and display recipes in UI
        // Check if response contains any hits
        Log.d("Response", "handleResponse")
        if (response.hits.isNotEmpty()) {
            // Extract recipe details from response and add them to the adapter
            val recipes = response.hits.map { it.recipe }
            Log.d("recipes",recipes.toString())
            adapter.clear()
            adapter.addAll(recipes)
        } else {
            // Display a message indicating no recipes found
            // You can show this message in a TextView or Toast
        }
    }
}