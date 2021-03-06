package ru.startandroid.onlinesim.data

import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

class Data {

    data class Country(val Id:Int, val Name:String)

    data class ServicePrices( val id: String, val count: @NotNull BigDecimal, val price: Int)

    data class AccountBalance(val balance: @NotNull BigDecimal)

    data class NumberPhone(val activation: Int=0, val numberPhone: Long=0, val service:String="")

}
