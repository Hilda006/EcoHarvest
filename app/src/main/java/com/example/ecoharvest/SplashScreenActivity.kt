package com.example.ecoharvest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.ecoharvest.databinding.ActivitySplashScreenBinding
import com.example.ecoharvest.ui.LoginActivity
import com.example.ecoharvest.ui.MainActivity
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)


        android.os.Handler().postDelayed({

            val intent = Intent(
                this@SplashScreenActivity, LoginActivity::class.java
            )

            startActivity(intent)

            finish()
        }, 2000)
    }



}


