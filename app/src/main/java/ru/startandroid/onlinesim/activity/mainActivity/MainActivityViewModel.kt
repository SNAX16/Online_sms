package ru.startandroid.onlinesim.activity.mainActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter.*
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    init {
        onStart()
    }

    fun onStart() {
    }

    fun getBalance(): Data.AccountBalance? {
        var balance:Data.AccountBalance? = null
        GlobalScope.launch(Dispatchers.IO) {
            val apiAdapter = ApiAdapter(User.apyKey)
            balance = apiAdapter.getAccountBalance()
        }
        return balance
    }
}

