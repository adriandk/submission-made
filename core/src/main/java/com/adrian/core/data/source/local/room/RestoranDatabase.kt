package com.adrian.core.data.source.local.room

import androidx.room.*
import com.adrian.core.data.source.local.entity.RestoranEntity

@Database(entities = [RestoranEntity::class], version = 1, exportSchema = false)
abstract class RestoranDatabase : RoomDatabase() {
    abstract fun restoranDao(): RestoranDao
}