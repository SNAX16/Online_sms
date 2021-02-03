package ru.startandroid.onlinesim.activity.selectionActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import java.util.*

class SelectionServicesViewModel() : ViewModel() {
    val apiAdapter = ApiAdapter(User.apyKey)
    val liveDataPrice = MutableLiveData<ArrayList<Data.ServicePrices>>()
    val liveDataCountry = MutableLiveData<MutableList<String>>()

    init {
        onStart()
    }

    private fun onStart() {
        getCountry()
        getPrice(0)

    }

    fun getPrice(idCountry: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            liveDataPrice.postValue(apiAdapter.getServicePrices(idCountry))
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
}