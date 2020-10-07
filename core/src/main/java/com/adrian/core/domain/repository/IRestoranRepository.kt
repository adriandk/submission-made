package com.adrian.core.domain.repository

import com.adrian.core.data.Resource
import com.adrian.core.domain.model.Restoran
import kotlinx.coroutines.flow.Flow

interface IRestoranRepository {

    fun getAllRestoran(): Flow<Resource<List<Restoran>>>

    fun getFavoriteRestoran(): Flow<List<Restoran>>

    fun setFavoriteRestoran(restoran: Restoran, state: Boolean)

}