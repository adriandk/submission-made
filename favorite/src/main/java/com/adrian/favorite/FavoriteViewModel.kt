package com.adrian.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adrian.core.domain.usecase.RestoranUseCase

class FavoriteViewModel(restoranUseCase: RestoranUseCase) : ViewModel() {

    val favoriteRestoran = restoranUseCase.getFavoriteRestoran().asLiveData()

}