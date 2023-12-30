package com.example.dependencyinjection.common.dependencyInjection.app

import com.example.dependencyinjection.common.dependencyInjection.Retrofit1
import com.example.dependencyinjection.common.dependencyInjection.Retrofit2
import com.example.dependencyinjection.networking.StackoverflowApi
import com.example.dependencyinjection.networking.UrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    @Retrofit1
    fun retrofit1(urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlProvider.getBaseUrl1())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Retrofit2
    fun retrofit2(urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlProvider.getBaseUrl2())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun urlProvider() = UrlProvider()

    @Singleton
    @Provides
    fun stackoverflowApi(@Retrofit1 retrofit: Retrofit) =
        retrofit.create(StackoverflowApi::class.java)

}