package com.alex.testfirebaseproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alex.testfirebaseproject.databinding.ActivityUpdateBinding

class Update : AppCompatActivity() {

    private lateinit var binding :ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_update)

        binding = ActivityUpdateBinding.inflate(layoutInflater)

        binding.backButton.setOnClickListener {
            goBack()
        }
    }
    private fun goBack(){
        val searchIntent = Intent (this, Search::class.java)
        startActivity(searchIntent)
    }
}