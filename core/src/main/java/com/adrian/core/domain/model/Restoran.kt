package com.adrian.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restoran(
    val restoranId: String,
    val name: String,
    val description: String,
    val pictureId: String,
    val city: String,
    val rating: Double,
    var pictureLink: String = "https://dicoding-restaurant-api.el.r.appspot.com/images/small/$pictureId",
    val isFavorite: Boolean
) : Parcelable