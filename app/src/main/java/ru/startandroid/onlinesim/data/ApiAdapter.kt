package ru.startandroid.onlinesim.data

import org.jetbrains.annotations.NotNull
import ru.sms_activate.SMSActivateApi
import ru.sms_activate.client_enums.SMSActivateClientStatus
import ru.sms_activate.response.api_activation.SMSActivateSetStatusResponse
import ru.sms_activate.response.api_activation.enums.SMSActivateGetStatusActivation
import ru.sms_activate.response.api_activation.extra.SMSActivateCountryInfo
import java.util.ArrayList

class ApiAdapter(apiKey: String) : SMSActivateApi(apiKey) {
    private val listCountry: ArrayList<String> = ArrayList()

    suspend  fun getCountry(): ArrayList<String> {
        val country: MutableList<SMSActivateCountryInfo> = countries.smsActivateGetCountryInfoList

        country.forEach {
            listCountry.add(it.russianName)
        }
        return listCountry
    }

    suspend  fun getServicePrices(idCountry: Int): ArrayList<Data.ServicePrices> {
        val servicePrices= allPrices.getSmsActivateGetPriceMap(idCountry)
        val listServicePrices: ArrayList<Data.ServicePrices> = ArrayList()
       servicePrices.forEach {
           listServicePrices.add(Data.ServicePrices(it.key,it.value.cost,it.value.countPhoneNumbers))
       }

        return listServicePrices
    }

    fun getAccountBalance(): Data.AccountBalance {
        val accountBalance= balance
        return Data.AccountBalance(accountBalance)
    }


 fun getNumberPhone(idCountry: Int,nameService:String): Data.NumberPhone {
        val numberPhone = getNumber(idCountry,nameService)
        return  Data.NumberPhone(numberPhone.id,numberPhone.number,numberPhone.shortName)
    }

    suspend fun getStatusActivation( activationId:Int): String {
        var statusActivation:String= ""
        val status = getStatus(activationId)
        if (status.smsActivateGetStatus == SMSActivateGetStatusActivation.OK){
            statusActivation = status.codeFromSMS.toString()
        }

        if (status.smsActivateGetStatus == SMSActivateGetStatusActivation.WAIT_CODE){
            statusActivation = "Ожидайте"
        }
        if (status.smsActivateGetStatus == SMSActivateGetStatusActivation.CANCEL){
            statusActivation = "не обслуживается"
        }
        return statusActivation
    }

    fun getStatusIdActivation( activationId:Int): @NotNull SMSActivateGetStatusActivation {
        return getStatus(activationId).smsActivateGetStatus
    }

    suspend fun sSetStatus(activationId: Int, status: SMSActivateClientStatus): @NotNull SMSActivateSetStatusResponse {
        return setStatus(activationId,status)
    }
}
