package com.dicoding.picodiploma.loginwithanimation.data.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class StoriesResponse(

    @field:SerializedName("listStory")
    val listStory: List<StoriesItem> = emptyList(),

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class StoriesItem(

    @field:SerializedName("photoUrl")
    val photoUrl: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("lon")
    val lon: Double,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("lat")
    val lat: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(photoUrl)
        parcel.writeString(createdAt)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeValue(lon)
        parcel.writeString(id)
        parcel.writeValue(lat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoriesItem> {
        override fun createFromParcel(parcel: Parcel): StoriesItem {
            return StoriesItem(parcel)
        }

        override fun newArray(size: Int): Array<StoriesItem?> {
            return arrayOfNulls(size)
        }
    }
}
