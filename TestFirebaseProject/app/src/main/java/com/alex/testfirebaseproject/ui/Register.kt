package com.alex.testfirebaseproject.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alex.testfirebaseproject.data.Users
import com.alex.testfirebaseproject.databinding.ActivityRegisterBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class Register : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Analytics event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de firebase completa")
        analytics.logEvent("Initscreen", bundle)

        setup()
    }

    private fun setup(){
        binding.signInButton.setOnClickListener{
            goLogin()
        }


        binding.signUpButton.setOnClickListener {
            if (checkIfEmpty()){
              Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show()
            }else{
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(binding.email.text.toString(),
                    binding.password.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this, "Registration Succesful!", Toast.LENGTH_SHORT).show()
                        val newUser = Users(
                            binding.user.text.toString(),
                            binding.email.text.toString(),
                            binding.phone.text.toString(),
                        )

                        db.collection("Users").document(binding.email.text.toString())
                            .set(newUser)
                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                        goHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }else{
                        Toast.makeText(this, "Registration Unsuccesful!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
    private fun goLogin(){
        val loginIntent = Intent (this, Login::class.java)
        startActivity(loginIntent)
    }

    private fun goHome(email: String, provider: ProviderType) {
        val homeIntent = Intent (this, Home::class.java).apply{
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    //returns true if any field is empty
    private fun checkIfEmpty(): Boolean {
        val correo = binding.email.text.toString()
        val nombre = binding.user.text.toString()
        val numero = binding.phone.text.toString()

        var empty : Boolean = false

        if(correo.isNullOrEmpty() || nombre.isNullOrEmpty() ||
            numero.isNullOrEmpty()){
            empty = true
        }
        return empty
    }
}