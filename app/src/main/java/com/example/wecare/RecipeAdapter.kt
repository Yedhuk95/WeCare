import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wecare.R // Change this to your actual package name
import com.example.wecare.RecipeDetail // Change this to your actual package name

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val recipes: MutableList<RecipeDetail> = mutableListOf()

    fun addAll(newRecipes: List<RecipeDetail>) {
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }

    fun clear() {
        recipes.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewRecipeName: TextView = itemView.findViewById(R.id.textViewRecipeName)
        private val textViewRecipeUrl: TextView = itemView.findViewById(R.id.textViewRecipeUrl)

        fun bind(recipe: RecipeDetail) {
            textViewRecipeName.text = recipe.label
            textViewRecipeUrl.text = recipe.url
        }
    }
}
