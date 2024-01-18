package com.example.ecoharvest.data.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



data class Mart(
    var amount : String = "",
    var description : String = "",
    var image : String = "",
    var location : String = "",
    var name: String = "",

)