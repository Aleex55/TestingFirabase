package com.alex.testfirebaseproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alex.testfirebaseproject.databinding.ActivityRegisterBinding
import com.alex.testfirebaseproject.databinding.ActivitySearchBinding

class Search : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_search)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.editButton.setOnClickListener {
            val name = "nothing"
            goUpdate(name)
        }
        binding.backButton3.setOnClickListener {
            goBack()
        }
    }

    private fun goUpdate(name: String){
        val updateIntent = Intent (this, Update::class.java).apply {
            putExtra("name", name)
        }
        startActivity(updateIntent)
    }

    private fun goBack(){
        val homeIntent = Intent (this, Home::class.java)
        startActivity(homeIntent)
    }
}