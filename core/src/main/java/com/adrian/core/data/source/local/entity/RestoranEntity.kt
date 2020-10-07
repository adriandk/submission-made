package com.adrian.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restoran")
data class RestoranEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "restoranId")
    var restoranId: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "pictureId")
    var pictureId: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)