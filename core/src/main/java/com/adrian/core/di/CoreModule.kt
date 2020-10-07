package com.adrian.core.di

import androidx.room.Room
import com.adrian.core.data.RestoranRepository
import com.adrian.core.data.source.local.LocalDataSource
import com.adrian.core.data.source.local.room.RestoranDatabase
import com.adrian.core.data.source.remote.RemoteDataSource
import com.adrian.core.data.source.remote.network.ApiService
import com.adrian.core.domain.repository.IRestoranRepository
import com.adrian.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<RestoranDatabase>().restoranDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            RestoranDatabase::class.java, "Restoran.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostName = "dicoding-restaurant-api.el.r.appspot.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/WosIoHIqU0P1gNlxFHa16nhSTNS2fZVPIu8DHxqu8Hg=")
            .add(hostName, "sha256/YZPgTZ+woNCCCIW3LH2CxQeLzB/1m42QcCTBSdgayjs=")
            .add(hostName, "sha256/iie1VXtL7HzAMF+/PVPR9xzT80kQxdZeJ+zduCB3uj0=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dicoding-restaurant-api.el.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IRestoranRepository> {
        RestoranRepository(
            get(),
            get(),
            get()
        )
    }
}