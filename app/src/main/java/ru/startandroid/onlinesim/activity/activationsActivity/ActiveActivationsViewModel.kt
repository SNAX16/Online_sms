package ru.startandroid.onlinesim.activity.activationsActivity

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import java.util.ArrayList

class ActiveActivationsViewModel(application: Application) : AndroidViewModel(application) {

    val liveDataBalance = MutableLiveData<Data.AccountBalance>()
    val liveData = MutableLiveData<ArrayList<String>>()
    val liveDataPhoneNamber = MutableLiveData<Data.NumberPhone>()

    init {
        onStart()
    }

    fun onStart() {
        val selection: ArrayList<String> = ArrayList()

        for (i in 1..5) {
            selection.add("ID$i")
        }

         liveData.value = selection
        getBalance()


        //getPhoneNumber()



    }

    fun getBalance() {
        GlobalScope.launch(Dispatchers.IO) {
            val balance = ApiAdapter(User.apyKey)
            liveDataBalance.postValue(balance.getAccountBalance())
        }

    }

    fun getPhoneNumber(intent:Intent) {
        GlobalScope.launch(Dispatchers.IO) {
            val phoneNumber = ApiAdapter(User.apyKey)
            liveDataPhoneNamber.postValue(phoneNumber.getNumberPhone(intent.getStringExtra("idCountry").toInt(),intent.getStringExtra("services")))
        }

    }
}