package com.adrian.submission1

import android.app.Application
import com.adrian.core.di.databaseModule
import com.adrian.core.di.networkModule
import com.adrian.core.di.repositoryModule
import com.adrian.submission1.injection.useCaseModule
import com.adrian.submission1.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}