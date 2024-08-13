package com.example.testsubmissionsuitemedia.data.source.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apiretrofit {
    private const val BASE_URL = "https://reqres.in/"

    val apiService: Apiservice by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiservice::class.java)
    }
}