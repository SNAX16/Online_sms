package ru.startandroid.onlinesim.utilits

import androidx.recyclerview.widget.DiffUtil
import ru.startandroid.onlinesim.data.Data

open class DiffUtilCallbackServicePrices (private val oldList:List<Data.ServicePrices>,
                                          private var newList:List<Data.ServicePrices>): DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int =oldList.size
    override fun getNewListSize(): Int =newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}