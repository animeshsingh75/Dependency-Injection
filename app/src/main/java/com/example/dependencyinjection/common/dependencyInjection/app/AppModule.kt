package com.example.dependencyinjection.common.dependencyInjection.app

import android.app.Application
import com.example.dependencyinjection.common.dependencyInjection.Retrofit1
import com.example.dependencyinjection.common.dependencyInjection.Retrofit2
import com.example.dependencyinjection.networking.StackoverflowApi
import com.example.dependencyinjection.networking.UrlProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val application: Application) {

    @Provides
    @AppScope
    @Retrofit1
    fun retrofit1(urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlProvider.getBaseUrl1())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    @Retrofit2
    fun retrofit2(urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlProvider.getBaseUrl2())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun urlProvider() = UrlProvider()

    @Provides
    fun application() = application

    @AppScope
    @Provides
    fun stackoverflowApi(@Retrofit1 retrofit: Retrofit) =
        retrofit.create(StackoverflowApi::class.java)

}