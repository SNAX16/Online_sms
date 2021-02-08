package ru.startandroid.onlinesim.activity.activationsActivity

import android.app.Application
import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.jetbrains.annotations.NotNull
import ru.sms_activate.SMSActivateApi
import ru.sms_activate.client_enums.SMSActivateClientStatus
import ru.sms_activate.response.api_activation.SMSActivateSetStatusResponse
import ru.sms_activate.response.api_activation.enums.SMSActivateGetStatusActivation
import ru.sms_activate.response.api_activation.enums.SMSActivateServerStatus
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data
import ru.startandroid.onlinesim.model.database.DataBaseHelperImpl
import ru.startandroid.onlinesim.model.database.DatabaseBuilder
import ru.startandroid.onlinesim.model.entity.LiveActivations
import java.lang.Exception
import java.lang.Runnable
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

import kotlin.concurrent.schedule

class ActiveActivationsViewModel(application: Application) : AndroidViewModel(application) {
    private val apiAdapter = ApiAdapter(User.apyKey)
    val liveDataBalance = MutableLiveData<Data.AccountBalance>()
    val liveDataLiveActivations = MutableLiveData<List<LiveActivations>>()
    val app = application
    private val db = DataBaseHelperImpl(DatabaseBuilder.getInstance(app.applicationContext))

    init {
        onStart()
    }

    fun onStart() {
        getBalance()
        Executors.newSingleThreadScheduledExecutor()
            .scheduleAtFixedRate(Runnable { getStatusActivation() }, 0, 5, TimeUnit.SECONDS)
    }

    fun getBalance() {
        GlobalScope.launch(Dispatchers.IO) {
            val balance = ApiAdapter(User.apyKey)
            liveDataBalance.postValue(balance.getAccountBalance())
        }
    }

    fun getStatusActivation() {
        GlobalScope.launch(Dispatchers.IO) {
            val liveActivations: ArrayList<LiveActivations> = ArrayList()
            db.getAll().forEach {
                val statusActivation = apiAdapter.getStatusActivation(it.liveActivations_id)
                liveActivations.add(LiveActivations(it.liveActivations_id, it.service, it.idCountry, it.phoneNumber, statusActivation
                    )
                )
            }
            liveDataLiveActivations.postValue(liveActivations)
        }
    }
//
//    fun setStatusFinish(activationId: Int): @NotNull SMSActivateServerStatus {
//            val setStatus = SMSActivateApi(User.apyKey)
//            val response = setStatus.setStatus(activationId, SMSActivateClientStatus.FINISH)
//            response.smsActivateAccessStatus
//    }

    suspend fun setStatusCancel(activationId: Int) {

        val setStatus = ApiAdapter(User.apyKey)
        val response = setStatus.sSetStatus(activationId, SMSActivateClientStatus.CANCEL)
        response.smsActivateAccessStatus

    }

    fun deleteLiveActivations(activations: LiveActivations) {
        GlobalScope.launch(Dispatchers.IO) {
            db.deleteLiveActivations(activations)
        }
    }

    suspend fun setStatusCancel1(activationId: Int): @NotNull String {
        val job = GlobalScope.async(Dispatchers.IO) {
            val setStatus = ApiAdapter(User.apyKey)
            val response = setStatus.sSetStatus(activationId, SMSActivateClientStatus.CANCEL)
            response
        }
        return job.await().smsActivateAccessStatus.russianMessage
    }

    suspend fun setStatusFinish1(activationId: Int): @NotNull String {
        val job = GlobalScope.async(Dispatchers.IO) {
            val setStatus = ApiAdapter(User.apyKey)
            val response = setStatus.setStatus(activationId, SMSActivateClientStatus.FINISH)
            response
        }
        return job.await().smsActivateAccessStatus.russianMessage
    }

    suspend fun getStatus(activationId: Int): @NotNull SMSActivateGetStatusActivation {
        val job = GlobalScope.async(Dispatchers.IO) {
            val getStatusActivation = SMSActivateApi(User.apyKey)
            val response = getStatusActivation.getStatus(activationId).smsActivateGetStatus
            response
        }
        return job.await()
    }
}

