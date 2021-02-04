package ru.startandroid.onlinesim.data

import ru.sms_activate.SMSActivateApi
import ru.sms_activate.response.api_activation.enums.SMSActivateGetStatusActivation
import ru.sms_activate.response.api_activation.extra.SMSActivateCountryInfo
import java.util.ArrayList

class ApiAdapter(apiKey: String) : SMSActivateApi(apiKey) {
    private val listCountry: ArrayList<String> = ArrayList()

    fun getCountry(): ArrayList<String> {
        val country: MutableList<SMSActivateCountryInfo> = countries.smsActivateGetCountryInfoList

        country.forEach {
            listCountry.add(it.russianName)
        }
        return listCountry
    }

    fun getServicePrices(idCountry: Int): ArrayList<Data.ServicePrices> {
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

    fun getStatusActivation( activationId:Int): String {
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

    fun getStatusIdActivation( activationId:Int): Int? {
        var statusActivation:Int? =null
        val status = getStatus(activationId)
        if (status.smsActivateGetStatus == SMSActivateGetStatusActivation.OK){
            statusActivation = 2
        }
        if (status.smsActivateGetStatus == SMSActivateGetStatusActivation.WAIT_CODE){
            statusActivation = 1
        }
        if (status.smsActivateGetStatus == SMSActivateGetStatusActivation.CANCEL){
            statusActivation = 0
        }
        return statusActivation
    }
}
