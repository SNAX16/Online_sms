package ru.startandroid.onlinesim.data

import ru.sms_activate.SMSActivateApi
import ru.sms_activate.response.api_activation.extra.SMSActivateCountryInfo
import java.util.ArrayList

class ApiAdapter(apiKey: String) : SMSActivateApi(apiKey) {
    private val list: ArrayList<Data.Country> = ArrayList()
    private val listCountry: ArrayList<String> = ArrayList()
    private val listServicePrices: ArrayList<Data.ServicePrices> = ArrayList()

    fun getCountry(): ArrayList<String> {
        val country: MutableList<SMSActivateCountryInfo> = countries.smsActivateGetCountryInfoList

        country.forEach {
            listCountry.add("${it.id}  ${it.russianName}")
        }
        return listCountry
    }

    fun getServicePrices(idCountry: Int): ArrayList<Data.ServicePrices> {
        val servicePrices= allPrices.getSmsActivateGetPriceMap(idCountry)

       servicePrices.forEach {
           listServicePrices.add(Data.ServicePrices(it.key,it.value.cost,it.value.countPhoneNumbers))
       }

        return listServicePrices
    }
}