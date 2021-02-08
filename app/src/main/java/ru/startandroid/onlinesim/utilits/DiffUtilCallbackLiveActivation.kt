package ru.startandroid.onlinesim.utilits

import androidx.recyclerview.widget.DiffUtil
import ru.startandroid.onlinesim.model.entity.LiveActivations

open class DiffUtilCallbackLiveActivation (private val oldList:List<LiveActivations>,
                            private var newList:List<LiveActivations>): DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].liveActivations_id == newList[newItemPosition].liveActivations_id

    override fun getOldListSize(): Int =oldList.size
    override fun getNewListSize(): Int =newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}