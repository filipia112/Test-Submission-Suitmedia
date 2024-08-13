package com.example.testsubmissionsuitemedia.data.source.remote.network

import com.example.testsubmissionsuitemedia.data.source.remote.network.response.Apiresponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiservice {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<Apiresponse>
}