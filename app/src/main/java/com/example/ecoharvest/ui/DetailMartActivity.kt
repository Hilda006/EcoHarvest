package com.example.ecoharvest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecoharvest.R

class DetailMartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mart)
    }
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_LOCATION = "extra_location"
        const val EXTRA_AMOUNT = "extra_amount"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_IMG = "extra_img"
    }
}