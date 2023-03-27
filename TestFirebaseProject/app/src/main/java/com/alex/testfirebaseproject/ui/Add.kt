package com.alex.testfirebaseproject.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alex.testfirebaseproject.data.Dishes
import com.alex.testfirebaseproject.data.Users
import com.alex.testfirebaseproject.databinding.ActivityAddBinding
import com.alex.testfirebaseproject.databinding.ActivityRegisterBinding
import com.alex.testfirebaseproject.databinding.ActivityUpdateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Add : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email")
        val fixedWeight = "500g"
        val fixedRations = "3"
        val fixedIngredients = listOf("Random", "Love")
        val fixedDescription = "Just do it with some chicken"
        val fixedLocation = "here"

        binding.backButton2.setOnClickListener {
            goBack()
        }

        binding.addButton2.setOnClickListener {
            val newDish = Dishes(
                email.toString(),
                binding.nameET.text.toString(),
                fixedWeight,
                fixedRations,
                fixedIngredients,
                binding.priceET.text.toString(),
                fixedDescription,
                fixedLocation
            )

            db.collection("Dishes")
                .add(newDish)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
    private fun goBack(){
        val homeIntent = Intent (this, Home::class.java)
        startActivity(homeIntent)
    }
}