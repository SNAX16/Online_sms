package ru.startandroid.onlinesim.activity.selectionActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import java.util.*

class SelectionServicesViewModel(application: Application) : AndroidViewModel(application) {

    val liveDataPrice = MutableLiveData<ArrayList<Data.ServicePrices>>()
    val liveDataCountry = MutableLiveData<MutableList<String>>()

    init {
        onStart()
    }

    private fun onStart() {
        getCountry()
        getPrice(0)

    }

    fun getPrice(idCountru: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val country = ApiAdapter(User.apyKey)

            liveDataPrice.postValue(country.getServicePrices(idCountru))
        }

    }

    fun getCountry() {
        GlobalScope.launch(Dispatchers.IO) {
            val country = ApiAdapter(User.apyKey)

            liveDataCountry.postValue(country.getCountry())
        }

    }

}