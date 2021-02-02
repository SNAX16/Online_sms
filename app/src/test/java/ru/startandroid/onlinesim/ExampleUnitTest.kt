package ru.startandroid.onlinesim

import org.junit.Test

import org.junit.Assert.*
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.data.ApiAdapter

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
        val country = ApiAdapter("6863f0555d45c62d137A8b6e3c19d64f")
//        val sdfsdf= country.allPrices.countryIdSet
//        val servicePrices= country.allPrices.getSmsActivateGetPriceMap(0)
        println(country.getServicePrices(1))

    }

}