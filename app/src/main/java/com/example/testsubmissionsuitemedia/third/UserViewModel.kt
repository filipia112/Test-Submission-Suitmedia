package com.example.testsubmissionsuitemedia.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsubmissionsuitemedia.data.source.Resource
import com.example.testsubmissionsuitemedia.data.source.UserRepository
import com.example.testsubmissionsuitemedia.data.source.remote.network.response.Apiresponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _userState = MutableStateFlow<Resource<Apiresponse>>(Resource.Loading())
    val userState: StateFlow<Resource<Apiresponse>> = _userState

    fun fetchUsers(page: Int, perPage: Int) {
        viewModelScope.launch {
            userRepository.getAllUsers(page, perPage).collect { resource ->
                _userState.value = resource
            }
        }
    }
}