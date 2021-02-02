package ru.startandroid.onlinesim.data

import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

class Data {

    data class Country(val Id:Int, val Name:String)

    data class ServicePrices(val Id: String, val count: @NotNull BigDecimal, val price: Int)

}
