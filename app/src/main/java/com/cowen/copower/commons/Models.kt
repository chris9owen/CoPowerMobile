package com.cowen.copower.commons

import android.os.Parcel
import android.os.Parcelable
import com.cowen.copower.commons.adapter.AdapterConstants
import com.cowen.copower.commons.adapter.ViewType

data class CopowerChallenges(
        val challenges: List<Challenge>?) : Parcelable {
    companion object {
        @Suppress("unused")
        @JvmField val CREATOR: Parcelable.Creator<CopowerChallenges> = object : Parcelable.Creator<CopowerChallenges> {
            override fun createFromParcel(source: Parcel): CopowerChallenges = CopowerChallenges(source)
            override fun newArray(size: Int): Array<CopowerChallenges?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.createTypedArrayList(
        Challenge.CREATOR
    ))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeTypedList(challenges)
    }
}

data class Challenge(
        val id: Int,
        val title: String,
        val text: String
) : ViewType, Parcelable {

    override fun getViewType() = AdapterConstants.CHALLENGES

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Challenge> = object : Parcelable.Creator<Challenge> {
            override fun createFromParcel(source: Parcel): Challenge = Challenge(source)
            override fun newArray(size: Int): Array<Challenge?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readString()?:"", source.readString()?:"")

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(title)
        dest?.writeString(text)
    }
}