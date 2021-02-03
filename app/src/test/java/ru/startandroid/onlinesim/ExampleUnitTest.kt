package ru.startandroid.onlinesim

import org.junit.Test

import org.junit.Assert.*
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
        val apiAdapter = ApiAdapter(User.apyKey)
        val numberPhone =apiAdapter.getNumber(0,"vk")
        println(numberPhone)
    }

}