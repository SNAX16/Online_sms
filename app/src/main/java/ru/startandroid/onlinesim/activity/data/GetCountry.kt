package ru.startandroid.onlinesim.activity.data

import ru.sms_activate.SMSActivateApi
import ru.sms_activate.response.api_activation.extra.SMSActivateCountryInfo
import ru.startandroid.onlinesim.auth.User
import java.util.ArrayList

class GetCountry(apiKey: String) :SMSActivateApi(apiKey) {

    fun longRunningMethod(): ArrayList<User.Info> {
        val list: ArrayList<User.Info> = ArrayList()
          val country:MutableList<SMSActivateCountryInfo> = countries.smsActivateGetCountryInfoList

        country.forEach {
            list.add(
                User.Info(
                it.id,it.russianName
            ))
        }
        return list
    }
}