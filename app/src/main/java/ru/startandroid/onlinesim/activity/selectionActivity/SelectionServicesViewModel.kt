package ru.startandroid.onlinesim.activity.selectionActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import ru.startandroid.onlinesim.model.database.DataBaseHelperImpl
import ru.startandroid.onlinesim.model.database.DatabaseBuilder
import java.util.*

class SelectionServicesViewModel(application: Application) : AndroidViewModel(application) {
    private val apiAdapter = ApiAdapter(User.apyKey)
    private val db = DataBaseHelperImpl(DatabaseBuilder.getInstance(application.applicationContext))
    val liveDataPrice = MutableLiveData<ArrayList<Data.ServicePrices>>()
    val liveDataCountry = MutableLiveData<MutableList<String>>()
    val liveDataCounterBuy = MutableLiveData<Int>()

    init {
        onStart()
    }

    private fun onStart() {
        getCountry()
        getPrice(0)
        setCounterBuy()
    }


    fun getPrice(idCountry: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val price= apiAdapter.getServicePrices(idCountry)
            liveDataPrice.postValue(price)
        }

    }

    fun getCountry() {
        GlobalScope.launch(Dispatchers.IO) {
            liveDataCountry.postValue(apiAdapter.getCountry())
        }
    }

    fun getPhoneNumber(idCountry:Int,services:String): Data.NumberPhone {
        return apiAdapter.getNumberPhone(idCountry,services)
    }

    fun setCounterBuy(){
        CoroutineScope(Dispatchers.IO).launch {
            val counterBuy = db.getAll()
            liveDataCounterBuy.postValue(counterBuy.size)
        }
    }
}