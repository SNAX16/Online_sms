package ru.startandroid.onlinesim.activity.selectionActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.activationsActivity.ActiveActivations
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import ru.startandroid.onlinesim.model.database.DataBaseHelperImpl
import ru.startandroid.onlinesim.model.database.DatabaseBuilder
import ru.startandroid.onlinesim.model.entity.LiveActivations


class SelectionAdapter(
    val selection: List<Data.ServicePrices>,
    val context: Context,
    val idCountry: Int,
    val ssViewModel: SelectionServicesViewModel
) : RecyclerView.Adapter<SelectionAdapter.ViewHolder>() {
    private var spinnerPosition: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.services_layout, parent, false)
        return ViewHolder(
            view
        )
    }


    override fun getItemCount() = selection.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon_service)
        val servicesText: TextView = itemView.findViewById(R.id.name_services)
        val countNumberText: TextView = itemView.findViewById(R.id.count_number)
        val priceServicesText: TextView = itemView.findViewById(R.id.price_services)
        val buyServices: ImageButton = itemView.findViewById(R.id.buy_services)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide
            .with(context)
            .load("https://sms-activate.ru/assets/ico/${selection[position].Id}0.png")
            .into(holder.icon)
        holder.servicesText.text = selection[position].Id ?: "Vk"
        holder.countNumberText.text = selection[position].price.toString() ?: "000"
        holder.priceServicesText.text = selection[position].count.toString() + "Р" ?: "000"


        holder.buyServices.setOnClickListener {
            saveData(idCountry, selection[position].Id)
            val intent = Intent(context, ActiveActivations::class.java)
            startActivity(intent)
        }
    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent, null)
    }


    fun saveData( idCountry: Int,service: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val apiAdapter = ApiAdapter(User.apyKey)
            val numberPhone   = apiAdapter.getNumberPhone(idCountry, service)
            val db = DataBaseHelperImpl(DatabaseBuilder.getInstance(context))
            db.addLiveActivations(LiveActivations(numberPhone.activation, service,idCountry, numberPhone.numberPhone)
            )
        }
    }

}