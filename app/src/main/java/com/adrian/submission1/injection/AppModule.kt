package com.adrian.submission1.injection

import com.adrian.core.domain.usecase.RestoranInteractor
import com.adrian.core.domain.usecase.RestoranUseCase
import com.adrian.submission1.detail.DetailViewModel
import com.adrian.submission1.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RestoranUseCase> { RestoranInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}