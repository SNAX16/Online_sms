package ru.startandroid.onlinesim

import org.junit.Test

import org.junit.Assert.*
import ru.startandroid.onlinesim.activity.data.GetCountry

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
       val country= GetCountry("6863f0555d45c62d137A8b6e3c19d64f")

        println(country.longRunningMethod())
    }

   }