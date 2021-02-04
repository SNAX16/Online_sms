package ru.startandroid.onlinesim.activity.activationsActivity

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.model.entity.LiveActivations

class ActivationsAdapter(var selection: List<LiveActivations>, val context:Context,val aaViewModel: ActiveActivationsViewModel): RecyclerView.Adapter<ActivationsAdapter.ViewHolder>(){
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
        val phoneNumber:TextView = itemView.findViewById(R.id.count_number_a)
        val kodServices:TextView = itemView.findViewById(R.id.kod_services)
        val acceptButton:ImageButton = itemView.findViewById(R.id.accept_button)
        val cancelButton:ImageButton = itemView.findViewById(R.id.cancel_button)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idActivation.text = selection[position].liveActivations_id.toString()
        Glide.with(context)
            .load("https://sms-activate.ru/assets/ico/${selection[position].service}0.png")
            .into(holder.iconService)
        Glide.with(context)
            .load("https://sms-activate.ru/assets/ico/country/${selection[position].idCountry}.png")
            .into(holder.iconCountry)
        holder.phoneNumber.text = selection[position].phoneNumber.toString()
        holder.kodServices.text = selection[position].kodSms.toString()

        holder.acceptButton.setOnClickListener {
                when (aaViewModel.getStatusActivationId(selection[position].liveActivations_id)) {
                    2 -> {
                        val mAlertDialog = AlertDialog.Builder(context)
                        mAlertDialog
                            .setTitle("Подтверждение")
                            .setMessage("Подтвердить использование номера?")
                            .setPositiveButton("Подтвердить") { _, _ ->
                                aaViewModel.setStatusFinish(selection[position].liveActivations_id)
                                aaViewModel.getBalance()
                            }
                            .setNegativeButton("Отменить") { dialog, _ -> dialog.cancel() }
                        mAlertDialog.show()
                    }
                    1 -> {
                        Toast.makeText(context, "Ожидаем смс", Toast.LENGTH_SHORT).show()
                    }

                    else -> Toast.makeText(context, "Номер не обслуживаеться", Toast.LENGTH_SHORT)
                        .show()
                }
        }
        holder.cancelButton.setOnClickListener {
            when (aaViewModel.getStatusActivationId(selection[position].liveActivations_id)) {
                2 -> {
                    val mAlertDialog = AlertDialog.Builder(context)
                    mAlertDialog
                        .setTitle("Отмена невозможна")
                        .setMessage("Ваш код ${selection[position].kodSms}")
                        .setNegativeButton("Отменить") { dialog, _ -> dialog.cancel() }
                    mAlertDialog.show()
                }
                else -> {
                    val mAlertDialog = AlertDialog.Builder(context)
                    mAlertDialog
                        .setTitle("Подтверждение")
                        .setMessage("Вы уверенны что хотите отменить активацию?")
                        .setPositiveButton("Подтвердить") { _, _ ->
                            aaViewModel.setStatusCancel(selection[position].liveActivations_id)
                            aaViewModel.deleteLiveActivations(selection[position])
                            aaViewModel.getBalance()
                        }
                        .setNegativeButton("Отменить") { dialog, _ -> dialog.cancel() }
                    mAlertDialog.show() }
                }
            }

            holder.phoneNumber.setOnClickListener {
                val clipboard: ClipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip =
                    ClipData.newPlainText("Код скопирован", holder.phoneNumber.text.toString())
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Номер скопирован", Toast.LENGTH_SHORT).show()
            }


            holder.kodServices.setOnClickListener {
                val clipboard: ClipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip =
                    ClipData.newPlainText("Код скопирован", holder.kodServices.text.toString())
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Код скопирован", Toast.LENGTH_SHORT).show()
            }
        }

        private fun startActivity(intent: Intent) {
            context.startActivity(intent, null)
        }
    }
