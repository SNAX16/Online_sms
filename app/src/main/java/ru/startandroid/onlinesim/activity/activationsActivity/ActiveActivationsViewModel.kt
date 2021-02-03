package ru.startandroid.onlinesim.activity.activationsActivity

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import ru.startandroid.onlinesim.model.database.DataBaseHelperImpl
import ru.startandroid.onlinesim.model.database.DatabaseBuilder
import ru.startandroid.onlinesim.model.entity.LiveActivations
import java.util.*

class ActiveActivationsViewModel(application: Application) : AndroidViewModel(application) {

    val liveDataBalance = MutableLiveData<Data.AccountBalance>()
    val liveData = MutableLiveData<ArrayList<String>>()
    val liveDataPhoneNamber = MutableLiveData<ArrayList<String>>()
    val liveDatainter = MutableLiveData<List<LiveActivations>>()
    val app = application

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
        getlive()
    }

    fun getBalance() {
        GlobalScope.launch(Dispatchers.IO) {
            val balance = ApiAdapter(User.apyKey)
            liveDataBalance.postValue(balance.getAccountBalance())
        }
    }

    fun getlive() {
        GlobalScope.launch(Dispatchers.IO) {
            val db = DataBaseHelperImpl(DatabaseBuilder.getInstance(app.applicationContext))
            liveDatainter.postValue(db.getAll())
        }
    }
}

