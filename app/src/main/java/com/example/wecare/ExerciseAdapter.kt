import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wecare.R
import com.example.wecare.Exercise // Assuming Exercise is the data class for exercise details

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private val exercises: MutableList<Exercise> = mutableListOf()

    fun addExercise(exercise: List<Exercise>) {
        exercises.addAll(exercise)
        notifyItemInserted(exercises.size - 1)
    }

    fun clear() {
        exercises.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewExerciseName: TextView = itemView.findViewById(R.id.textViewExerciseName)
        private val textViewExerciseDescription: TextView = itemView.findViewById(R.id.textViewExerciseDescription)

        fun bind(exercise: Exercise) {
            textViewExerciseName.text = exercise.name
            textViewExerciseDescription.text = exercise.instructions
        }
    }
}
