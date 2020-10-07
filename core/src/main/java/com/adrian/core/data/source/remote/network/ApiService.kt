package com.adrian.core.data.source.remote.network

import com.adrian.core.data.source.remote.response.ListRestoranResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListRestoranResponse
}