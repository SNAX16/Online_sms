package ru.startandroid.onlinesim.activity.selectionActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import ru.sms_activate.SMSActivateApi
import ru.sms_activate.response.api_activation.extra.SMSActivateCountryInfo
import ru.startandroid.onlinesim.activity.data.GetCountry
import ru.startandroid.onlinesim.auth.User
import java.util.ArrayList

class SelectionServicesViewModel(application: Application) : AndroidViewModel(application) {
    val smsActivateApi = SMSActivateApi("6863f0555d45c62d137A8b6e3c19d64f")
    val selection:  ArrayList<String> = ArrayList()

val liveData = MutableLiveData<ArrayList<String>>()
val liveData1 = MutableLiveData<ArrayList<String>>()
val liveData2 = MutableLiveData<List<User.Info>>()

    init {
        onStart()
    }

 fun onStart() {

     val selection1: ArrayList<String> = ArrayList()
     val selection2: ArrayList<String> = ArrayList()


     for (i in 1..30) {
         selection1.add("Одноклассники$i")
     }
     selection2.add("Gjkmif")



     liveData.value = selection2
     liveData1.value = selection1

     val job: Job = GlobalScope.launch(Dispatchers.IO) {
         longRunningMethod()
     }

 }

    private fun longRunningMethod() {
        val country = GetCountry("6863f0555d45c62d137A8b6e3c19d64f")

        liveData2.value=country.longRunningMethod()

    }


}