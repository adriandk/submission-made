package com.adrian.core.data.source.local

import com.adrian.core.data.source.local.entity.RestoranEntity
import com.adrian.core.data.source.local.room.RestoranDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val restoranDao: RestoranDao) {

    fun getAllRestoran(): Flow<List<RestoranEntity>> = restoranDao.getAllRestoran()

    fun getFavoriteRestoran(): Flow<List<RestoranEntity>> = restoranDao.getFavoriteRestoran()

    suspend fun insertRestoran(restoranList: List<RestoranEntity>) =
        restoranDao.insertRestoran(restoranList)

    fun setFavoriteRestoran(restoran: RestoranEntity, newState: Boolean) {
        restoran.isFavorite = newState
        restoranDao.updateFavoriteRestoran(restoran)
    }

}