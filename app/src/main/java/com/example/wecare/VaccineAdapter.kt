import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wecare.R

class MyAdapter(private val vaccineDescriptions: Array<String>, private val daysRemaining: IntArray) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    fun clear(){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vaccine, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var desc=vaccineDescriptions[position]
        val daysleft=daysRemaining[position]

        holder.bind(desc,daysleft)
    }

    override fun getItemCount(): Int {
        return vaccineDescriptions.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textDaysRemaining: TextView = itemView.findViewById(R.id.text_days_remaining)
        val textVaccineDescription: TextView = itemView.findViewById(R.id.text_vaccine_description)
        fun bind(desc:String,daysleft:Int)
        {
            if(daysleft>=0)
                {
                    val years = daysleft / 365
                    var remainingDays = daysleft % 365
                    val months = remainingDays / 30
                    remainingDays %= 30
                    val weeks = remainingDays / 7
                    remainingDays %= 7
                    if(years==0)
                    {
                        if(months==0)
                        {
                            if(weeks==0)
                            {
                                textDaysRemaining.text = "Time Remaining: "+weeks.toString()+" weeks "+remainingDays.toString()+" days"
                            }
                            else
                                textDaysRemaining.text = "Time Remaining: "+weeks.toString()+" weeks "+remainingDays.toString()+" days"
                        }
                        else
                            textDaysRemaining.text = "Time Remaining: "+months.toString()+" months "+weeks.toString()+" weeks "+remainingDays.toString()+" days"
                    }
                    else
                        textDaysRemaining.text = "Time Remaining: "+years.toString()+" years "+months.toString()+" months "+weeks.toString()+" weeks "+remainingDays.toString()+" days"
                }
            else
                textDaysRemaining.text="You have missed the deadline, Please get the vaccination done as soon as possible"

            textVaccineDescription.text = "Description: "+desc

        }

    }
}

