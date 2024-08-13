package com.example.testsubmissionsuitemedia.second

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testsubmissionsuitemedia.databinding.ActivitySecondBinding
import com.example.testsubmissionsuitemedia.main.MainActivity
import com.example.testsubmissionsuitemedia.third.ThirdActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("EXTRA_NAME")
        val userName = intent.getStringExtra("EXTRA_USER_NAME")
        if (userName != null) {
            binding.tvSelectedUsername.text = userName
        }
        binding.TvName.text = name
        binding.btnBack.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnNavigateListUser.setOnClickListener{
            val intent =Intent(this,ThirdActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}