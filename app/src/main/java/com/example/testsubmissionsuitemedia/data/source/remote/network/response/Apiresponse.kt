package com.example.testsubmissionsuitemedia.data.source.remote.network.response

data class Apiresponse(
	val perPage: Int? = null,
	val total: Int? = null,
	val data: List<DataItem?>? = null,
	val page: Int? = null,
	val totalPages: Int? = null
)

data class DataItem(
	val lastName: String? = null,
	val id: Int? = null,
	val avatar: String? = null,
	val firstName: String? = null,
	val email: String? = null
)

