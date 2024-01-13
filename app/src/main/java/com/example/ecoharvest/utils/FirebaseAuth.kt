package com.example.ecoharvest.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseAuth {
     val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser : FirebaseUser? = firebaseAuth.currentUser
}