package com.adrian.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RestoranResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("pictureId")
    val pictureId: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("rating")
    val rating: Double,
)