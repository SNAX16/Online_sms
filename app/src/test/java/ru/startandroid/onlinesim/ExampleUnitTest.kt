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
      val setStatus = SMSActivateApi("6863f0555d45c62d137A8b6e3c19d64f")
//        //setStatus.setStatus(387828131, SMSActivateClientStatus.MESSAGE_WAS_SENT)
//      //  setStatus.setStatus(387829529, SMSActivateClientStatus.FINISH)
//        val put =setStatus.getStatus(387829529)
        val servicePrices= setStatus.getStatus(387870217)

        println(servicePrices.smsActivateGetStatus)
    }

}