package com.adrian.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListRestoranResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("restaurants")
    val restoran: List<RestoranResponse>

)