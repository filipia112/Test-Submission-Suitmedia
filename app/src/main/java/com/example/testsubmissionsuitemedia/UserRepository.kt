package com.example.testsubmissionsuitemedia

import com.example.testsubmissionsuitemedia.data.source.remote.network.Apiretrofit
import com.example.testsubmissionsuitemedia.data.source.remote.network.response.Apiresponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class UserRepository {
    private val apiService = Apiretrofit.apiService

    fun getUsers(page: Int, perPage: Int): Flow<Resource<Apiresponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.getUsers(page, perPage)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error("Response body is null"))
            } else {
                emit(Resource.Error("Error: ${response.message()}"))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Network error: ${e.message}"))
        } catch (e: HttpException) {
            emit(Resource.Error("HTTP error: ${e.message()}"))
        }
    }
}