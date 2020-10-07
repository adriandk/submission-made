package com.adrian.core.data.source.remote

import android.util.Log
import com.adrian.core.data.source.remote.network.ApiResponse
import com.adrian.core.data.source.remote.network.ApiService
import com.adrian.core.data.source.remote.response.RestoranResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllRestoran(): Flow<ApiResponse<List<RestoranResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.restoran

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.restoran))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                Log.e("remote data source", "failed")
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}