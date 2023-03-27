package com.alex.testfirebaseproject.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alex.testfirebaseproject.databinding.ActivityLoginBinding

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Analytics event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de firebase completa")
        analytics.logEvent("Initscreen", bundle)
        //setup
        setup()
        session()
    }

    private fun session(){
        //val preferencia: SharedPreferences = getSharedPreferences()
    }

    private fun setup(){


        binding.signUpButton.setOnClickListener{
            goRegister()
        }

        binding.logInButton.setOnClickListener {
            if (binding.emailET.text.isNotEmpty() && binding.passwordET.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.emailET.text.toString(),
                        binding.passwordET.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(this, "Log in Succesful!", Toast.LENGTH_SHORT).show()
                            goHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            Toast.makeText(this, "Log in Unsuccesful!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
    private fun goHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, Home::class.java).apply{
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
    private fun goRegister(){
        val registerIntent = Intent (this, Register::class.java)
        startActivity(registerIntent)
    }
}