package com.alex.testfirebaseproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alex.testfirebaseproject.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth


enum class ProviderType{
    BASIC
}
class Home: AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup
        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email")
        val provider =bundle?.getString("provider")
        setup(email ?:"Error getting the email", provider ?:"Error getting the provider")
    }

    private fun setup(email: String, provider: String){
        binding.email.text = email
        binding.provider.text = provider

        binding.logoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            finish()
        }

        binding.searchButton.setOnClickListener{
            goSearch()
        }

        binding.addButton.setOnClickListener{
            goAdd(email)
        }
    }

    private fun goSearch(){
        val searchIntent = Intent(this, Search::class.java)
        startActivity(searchIntent)
    }
    private fun goAdd(email: String){
        val addIntent = Intent(this, Add::class.java).apply {
            putExtra("email", email)
        }
        startActivity(addIntent)
    }
}