package com.example.dependencyinjection.common.dependencyInjection

import android.app.Application
import androidx.annotation.UiThread
import com.example.dependencyinjection.Constants
import com.example.dependencyinjection.networking.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@UiThread
class AppCompositionRoot(val application: Application) {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val stackoverflowApi: StackoverflowApi by lazy {
        retrofit.create(StackoverflowApi::class.java)
    }


}