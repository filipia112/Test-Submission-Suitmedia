package com.example.testsubmissionsuitemedia.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testsubmissionsuitemedia.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("EXTRA_NAME")
        binding.TvName.text = name
    }
}