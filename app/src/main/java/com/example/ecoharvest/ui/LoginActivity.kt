package com.example.ecoharvest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ecoharvest.databinding.ActivityLoginBinding
import com.example.ecoharvest.utils.FirebaseAuth.firebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
    private fun login(){
        binding.progressBar.visibility = View.VISIBLE
        val email = binding.emailText.text.toString()
        val password = binding.passwordEditText.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    binding.progressBar.visibility = View.GONE
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()
        }
    }
}