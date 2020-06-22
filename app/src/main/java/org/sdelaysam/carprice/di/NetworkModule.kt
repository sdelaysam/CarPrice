package org.sdelaysam.carprice.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.sdelaysam.carprice.data.model.ServiceType
import org.sdelaysam.carprice.data.model.baseUrl
import org.sdelaysam.carprice.util.rx.RxSchedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import timber.log.Timber

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

val networkModule = module {

    factory<Retrofit> {
        val serviceType = it.get<ServiceType>(0)
        val json = get<Json>()
        val client = get<OkHttpClient>()
        Retrofit.Builder()
            .baseUrl(serviceType.baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(RxSchedulers.network))
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }

    factory<OkHttpClient> {
        val logger =  HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message ->
                if (message.length > 1000) {
                    Timber.d(message.substring(0, 1000))
                } else {
                    Timber.d(message)
                }
            })
            .apply { level = HttpLoggingInterceptor.Level.BODY }

        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

}

