package com.adrian.submission1.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adrian.core.domain.usecase.RestoranUseCase

class HomeViewModel(restoranUseCase: RestoranUseCase) : ViewModel() {
    val restoran = restoranUseCase.getAllRestoran().asLiveData()
}