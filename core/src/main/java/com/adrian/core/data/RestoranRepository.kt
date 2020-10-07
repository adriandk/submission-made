package com.adrian.core.data

import com.adrian.core.data.source.local.LocalDataSource
import com.adrian.core.data.source.remote.RemoteDataSource
import com.adrian.core.data.source.remote.network.ApiResponse
import com.adrian.core.data.source.remote.response.RestoranResponse
import com.adrian.core.domain.model.Restoran
import com.adrian.core.domain.repository.IRestoranRepository
import com.adrian.core.utils.AppExecutors
import com.adrian.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestoranRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRestoranRepository {

    override fun getAllRestoran(): Flow<Resource<List<Restoran>>> =
        object : NetworkBoundResource<List<Restoran>, List<RestoranResponse>>() {
            override fun loadFromDB(): Flow<List<Restoran>> {
                return localDataSource.getAllRestoran().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Restoran>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<RestoranResponse>>> =
                remoteDataSource.getAllRestoran()

            override suspend fun saveCallResult(data: List<RestoranResponse>) {
                val restoranList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertRestoran(restoranList)
            }
        }.asFlow()

    override fun getFavoriteRestoran(): Flow<List<Restoran>> {
        return localDataSource.getFavoriteRestoran().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteRestoran(restoran: Restoran, state: Boolean) {
        val restoranEntity = DataMapper.mapDomainToEntity(restoran)
        appExecutors.diskIO().execute { localDataSource.setFavoriteRestoran(restoranEntity, state) }
    }
}