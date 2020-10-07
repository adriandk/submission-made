package com.adrian.submission1.detail

import androidx.lifecycle.ViewModel
import com.adrian.core.domain.model.Restoran
import com.adrian.core.domain.usecase.RestoranUseCase

class DetailViewModel(private val restoranUseCase: RestoranUseCase) : ViewModel() {

    fun setFavoriteRestoran(restoran: Restoran, newStat: Boolean) =
        restoranUseCase.setFavoriteRestoran(restoran, newStat)

}