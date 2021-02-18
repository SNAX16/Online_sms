package ru.startandroid.onlinesim

import org.junit.Test

import org.junit.Assert.*
import ru.sms_activate.SMSActivateApi
import ru.sms_activate.client_enums.SMSActivateClientStatus
import ru.sms_activate.response.api_activation.enums.SMSActivateGetStatusActivation
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter
import ru.startandroid.onlinesim.data.Data

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun one() {
      val setStatus = SMSActivateApi("4f467be259d6f64842dbdA471228e2bc")
      val put=  setStatus.countries.smsActivateGetCountryInfoList

        println(put)
    }

}