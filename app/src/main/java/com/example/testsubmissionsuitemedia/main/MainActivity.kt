package com.example.testsubmissionsuitemedia.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testsubmissionsuitemedia.R
import com.example.testsubmissionsuitemedia.databinding.ActivityMainBinding
import com.example.testsubmissionsuitemedia.second.SecondActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCheckPalindrome.setOnClickListener {
            val sentence = binding.idPalindrome.text.toString().trim()
            val isPalindrome = checkTextPalindrome(sentence)
            showResultDialog(isPalindrome)
        }
        binding.btnNavigateNext.setOnClickListener {
            val name = binding.idName.text.toString()
            if (name.isBlank()) {
                Toast.makeText(this, R.string.not_empty, Toast.LENGTH_SHORT).show()
            } else {
                val data = mapOf("EXTRA_NAME" to name)
                val intent = Intent(this, SecondActivity::class.java)
                for ((key, value) in data) {
                    intent.putExtra(key, value)
                }
                startActivity(intent)
            }
        }

    }
    private fun checkTextPalindrome(sentence: String): Boolean {
        val cleanSentence = sentence.replace("\\s+".toRegex(), "").lowercase()
        return cleanSentence == cleanSentence.reversed()
    }
    private fun showResultDialog(isPalindrome: Boolean) {
        val message = if (isPalindrome) R.string.truePalindrome
        else R.string.falsePalindrome
        AlertDialog.Builder(this)
            .setTitle(R.string.result)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}