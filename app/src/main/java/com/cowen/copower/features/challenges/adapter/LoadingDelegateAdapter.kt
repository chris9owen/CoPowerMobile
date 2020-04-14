package com.cowen.copower.features.challenges.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cowen.copower.R
import com.cowen.copower.commons.adapter.ViewType
import com.cowen.copower.commons.adapter.ViewTypeDelegateAdapter
import com.cowen.copower.commons.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.challenge_loading))
}