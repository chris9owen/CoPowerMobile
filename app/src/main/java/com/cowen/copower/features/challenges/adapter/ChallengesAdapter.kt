package com.cowen.copower.features.challenges.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.cowen.copower.commons.Challenge
import com.cowen.copower.commons.adapter.AdapterConstants
import com.cowen.copower.commons.adapter.ViewType
import com.cowen.copower.commons.adapter.ViewTypeDelegateAdapter
import java.util.*

class ChallengesAdapter(listener: ChallengesDelegateAdapter.onViewSelectedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.CHALLENGES, ChallengesDelegateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addChallenges(challenges: List<Challenge>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(challenges)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddChallenges(challenges: List<Challenge>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(challenges)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getChallenges(): List<Challenge> =
            items
                .filter { it.getViewType() == AdapterConstants.CHALLENGES }
                .map { it as Challenge }


    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}