package com.adrian.core.utils

import com.adrian.core.data.source.local.entity.RestoranEntity
import com.adrian.core.data.source.remote.response.RestoranResponse
import com.adrian.core.domain.model.Restoran

object DataMapper {

    fun mapResponseToEntities(input: List<RestoranResponse>): List<RestoranEntity> {
        val restoranList = ArrayList<RestoranEntity>()
        input.map {
            val restoran = RestoranEntity(
                restoranId = it.id,
                name = it.name,
                description = it.description,
                pictureId = it.pictureId,
                city = it.city,
                rating = it.rating,
                isFavorite = false
            )
            restoranList.add(restoran)
        }
        return restoranList
    }

    fun mapEntitiesToDomain(input: List<RestoranEntity>): List<Restoran> =
        input.map {
            Restoran(
                restoranId = it.restoranId,
                name = it.name,
                description = it.description,
                pictureId = it.pictureId,
                city = it.city,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Restoran) = RestoranEntity(
        restoranId = input.restoranId,
        name = input.name,
        description = input.description,
        pictureId = input.pictureId,
        city = input.city,
        rating = input.rating,
        isFavorite = input.isFavorite
    )
}