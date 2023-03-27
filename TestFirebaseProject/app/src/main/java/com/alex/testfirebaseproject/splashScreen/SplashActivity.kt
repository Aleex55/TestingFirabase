package com.alex.testfirebaseproject.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alex.testfirebaseproject.ui.Login
import com.alex.testfirebaseproject.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        screenSplash.setKeepOnScreenCondition{ false }

        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}