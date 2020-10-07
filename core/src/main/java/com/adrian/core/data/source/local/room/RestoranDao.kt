package com.adrian.core.data.source.local.room

import androidx.room.*
import com.adrian.core.data.source.local.entity.RestoranEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestoranDao {

    @Query("SELECT * FROM restoran")
    fun getAllRestoran(): Flow<List<RestoranEntity>>

    @Query("SELECT * FROM restoran where isFavorite = 1")
    fun getFavoriteRestoran(): Flow<List<RestoranEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestoran(restoran: List<RestoranEntity>)

    @Update
    fun updateFavoriteRestoran(restoran: RestoranEntity)

}