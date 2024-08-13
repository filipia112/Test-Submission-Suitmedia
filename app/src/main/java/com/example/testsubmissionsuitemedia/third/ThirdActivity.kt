package com.example.testsubmissionsuitemedia.third

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testsubmissionsuitemedia.R
import com.example.testsubmissionsuitemedia.data.source.Resource
import com.example.testsubmissionsuitemedia.data.source.UserRepository
import com.example.testsubmissionsuitemedia.databinding.ActivityThirdBinding
import com.example.testsubmissionsuitemedia.second.SecondActivity
import com.example.testsubmissionsuitemedia.ui.UserAdapter
import com.example.testsubmissionsuitemedia.ui.ViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityThirdBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        userAdapter = UserAdapter { selectedUser ->
            selectedUser.firstName?.let {
                navigateToSecondActivity(it)
            }
        }
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = userAdapter
        binding.btnBack.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
        val viewModelFactory = ViewModelFactory(UserRepository())
        userViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
        userViewModel.userState.onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.rvUsers.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    binding.rvUsers.visibility = View.VISIBLE
                    resource.data?.data?.filterNotNull()?.let { users ->
                        userAdapter.setData(users)
                    }
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                    binding.rvUsers.visibility = View.GONE
                    binding.tvError.text = when {
                        resource.message?.contains("Network error", ignoreCase = true) == true ->
                            getString(R.string.error_network)
                        resource.message?.contains("HTTP error", ignoreCase = true) == true ->
                            getString(R.string.error_http)
                        else ->
                            getString(R.string.error_generic)
                    }
                }
            }
        }.launchIn(lifecycleScope)

        userViewModel.fetchUsers(page = 1, perPage = 10)
    }
    private fun navigateToSecondActivity(userName: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("EXTRA_USER_NAME", userName)
        startActivity(intent)
    }

}