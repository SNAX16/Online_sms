package ru.startandroid.onlinesim.activity.activationsActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.model.entity.LiveActivations

class ActivationsAdapter(var selection: List<LiveActivations>, val context:Context): RecyclerView.Adapter<ActivationsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view:View = LayoutInflater.from(parent.context).inflate(R.layout.activations_t_layout,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = selection.size
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val idActivation:TextView = itemView.findViewById(R.id.id_activation)
        val iconService:ImageView = itemView.findViewById(R.id.name_services_a)
        val iconCountry:ImageView = itemView.findViewById(R.id.name_country_a)
        val countNumber:TextView = itemView.findViewById(R.id.count_number_a)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idActivation.text = selection[position].liveActivations_id .toString()
        Glide.with(context).load("https://sms-activate.ru/assets/ico/${selection[position].service}0.png").into(holder.iconService)
        Glide.with(context).load("https://sms-activate.ru/assets/ico/country/${selection[position].idCountry}.png").into(holder.iconCountry)
        holder.countNumber.text = selection[position].phoneNumber.toString()
    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent, null)
    }

}