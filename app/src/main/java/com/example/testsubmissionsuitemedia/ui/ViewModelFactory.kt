package com.example.testsubmissionsuitemedia.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testsubmissionsuitemedia.data.source.UserRepository
import com.example.testsubmissionsuitemedia.third.UserViewModel

class ViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}