package com.adrian.core.domain.usecase

import com.adrian.core.domain.model.Restoran
import com.adrian.core.domain.repository.IRestoranRepository

class RestoranInteractor(private val restoranRepository: IRestoranRepository) : RestoranUseCase {

    override fun getAllRestoran() = restoranRepository.getAllRestoran()

    override fun getFavoriteRestoran() = restoranRepository.getFavoriteRestoran()

    override fun setFavoriteRestoran(restoran: Restoran, state: Boolean) =
        restoranRepository.setFavoriteRestoran(restoran, state)

}