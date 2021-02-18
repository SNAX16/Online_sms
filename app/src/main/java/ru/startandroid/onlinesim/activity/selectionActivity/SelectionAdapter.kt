package ru.startandroid.onlinesim.activity.selectionActivity

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import ru.startandroid.onlinesim.model.database.DataBaseHelperImpl
import ru.startandroid.onlinesim.model.database.DatabaseBuilder
import ru.startandroid.onlinesim.model.entity.LiveActivations
import ru.startandroid.onlinesim.utilits.DiffUtilCallbackServicePrices

private lateinit var mDiffResult:DiffUtil.DiffResult
var selection: List<Data.ServicePrices> = emptyList()
class SelectionAdapter(val context: Context, var idCountry: Int, val ssViewModel: SelectionServicesViewModel) : RecyclerView.Adapter<SelectionAdapter.ViewHolder>() {
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
            .load("https://sms-activate.ru/assets/ico/${selection[position].id}0.png")
            .into(holder.icon)
        holder.servicesText.text = selection[position].id ?: "Vk"
        holder.countNumberText.text = selection[position].price.toString() ?: "000"
        holder.priceServicesText.text = selection[position].count.toString() + "Р" ?: "000"


        holder.buyServices.setOnClickListener {
            val mAlertDialog = AlertDialog.Builder(context)
            .setTitle("Покупка")
            .setMessage("Вы уверены, что хотите приобрести номер?")
            .setPositiveButton("Подтвердить") { _, _ ->
                saveData(idCountry, selection[position].id)
            }

            .setNegativeButton("Отменить"){ dialog, id ->  dialog.cancel() }
            mAlertDialog.show()


        }
    }

    fun saveData(idCountry: Int, service: String) {
            CoroutineScope(Dispatchers.IO).launch {
                val apiAdapter = ApiAdapter(User.apyKey)
                val db = DataBaseHelperImpl(DatabaseBuilder.getInstance(context))
                try {
                    val numberPhone = apiAdapter.getNumber(idCountry, service)
                    db.addLiveActivations(LiveActivations(numberPhone.id, service, idCountry, numberPhone.number))
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, "Номер добавлен", Toast.LENGTH_SHORT).show()
                        ssViewModel.setCounterBuy()
                    }
                } catch (ex: Exception) {
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, "Ошибка получения номера", Toast.LENGTH_SHORT).show()
                    }
                }
            }

    }

    fun updateData(newList:List<Data.ServicePrices>, id:Int){
        mDiffResult = DiffUtil.calculateDiff(DiffUtilCallbackServicePrices(selection,newList))
        mDiffResult.dispatchUpdatesTo(this)
        selection = newList
        idCountry = id
    }
}