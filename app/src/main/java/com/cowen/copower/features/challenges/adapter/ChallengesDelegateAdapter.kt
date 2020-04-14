package com.cowen.copower.features.challenges.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cowen.copower.R
import com.cowen.copower.commons.Challenge
import com.cowen.copower.commons.adapter.ViewType
import com.cowen.copower.commons.adapter.ViewTypeDelegateAdapter
import com.cowen.copower.commons.extensions.getFriendlyTime
import com.cowen.copower.commons.extensions.inflate
import com.cowen.copower.commons.extensions.loadImg
import kotlinx.android.synthetic.main.challenge.view.*

class ChallengesDelegateAdapter(val viewActions: onViewSelectedListener) : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(url: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ChallengesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as ChallengesViewHolder
        holder.bind(item as Challenge)
    }

    inner class ChallengesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.challenge)) {

        private val imgThumbnail = itemView.img_thumbnail
        private val description = itemView.description
        private val author = itemView.author
        private val comments = itemView.comments
        private val time = itemView.time

        fun bind(item: Challenge) {
            //imgThumbnail.loadImg(item.thumbnail)
            description.text = item.title
            //author.text = item.author
            //comments.text = "${item.numComments} comments"
            //time.text = item.created.getFriendlyTime()

            //super.itemView.setOnClickListener { viewActions.onItemSelected(item.url)}
        }
    }
}