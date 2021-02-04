package ru.startandroid.onlinesim.activity.activationsActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.sms_activate.SMSActivateApi
import ru.sms_activate.client_enums.SMSActivateClientStatus
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import ru.startandroid.onlinesim.model.database.DataBaseHelperImpl
import ru.startandroid.onlinesim.model.database.DatabaseBuilder
import ru.startandroid.onlinesim.model.entity.LiveActivations
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ActiveActivationsViewModel(application: Application) : AndroidViewModel(application) {
    private val apiAdapter = ApiAdapter(User.apyKey)
    val liveDataBalance = MutableLiveData<Data.AccountBalance>()
    val liveDataLiveActivations = MutableLiveData<List<LiveActivations>>()
    val app = application
    val db = DataBaseHelperImpl(DatabaseBuilder.getInstance(app.applicationContext))

    init {
        onStart()
    }

    fun onStart() {
        getBalance()
        getlive()

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(Runnable { getStatusActivation() }, 0, 5, TimeUnit.SECONDS)
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
            liveDataLiveActivations.postValue(db.getAll())
        }
    }

    fun getStatusActivation() {
        GlobalScope.launch(Dispatchers.IO) {
            val liveActivations: ArrayList<LiveActivations> = ArrayList()
            db.getAll().forEach {
                val statusActivation = apiAdapter.getStatusActivation(it.liveActivations_id)
                liveActivations.add(LiveActivations(it.liveActivations_id, it.service, it.idCountry, it.phoneNumber, statusActivation))
            }
            liveDataLiveActivations.postValue(liveActivations)
        }
    }
    fun getStatusActivationId(activationId: Int): Int {
        var statusActivationId:Int=0
        GlobalScope.launch(Dispatchers.IO) {
            statusActivationId = apiAdapter.getStatusIdActivation(activationId)!!
        }
        return statusActivationId
    }

    fun setStatusFinish(activationId:Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val setStatus = SMSActivateApi(User.apyKey)
             setStatus.setStatus(activationId,SMSActivateClientStatus.FINISH)
        }
    }

    fun setStatusCancel(activationId:Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val setStatus = SMSActivateApi(User.apyKey)
            setStatus.setStatus(activationId,SMSActivateClientStatus.CANCEL)
        }
    }
    fun deleteLiveActivations(activations: LiveActivations) {
        GlobalScope.launch(Dispatchers.IO) {
           db.deleteLiveActivations(activations)
        }
    }

}

