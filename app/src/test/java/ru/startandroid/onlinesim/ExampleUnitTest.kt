package ru.startandroid.onlinesim

import org.junit.Test

import org.junit.Assert.*
import ru.sms_activate.SMSActivateApi

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
    fun one(){
        val smsActivateApi = SMSActivateApi("76752bd99858d2A9b2bAd634cA490895")
          println("${smsActivateApi.balance}")
        //println("${smsActivateApi.countries}")

    }
}