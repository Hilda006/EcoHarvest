package com.example.ecoharvest.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecoharvest.data.adapter.Mart
import com.example.ecoharvest.data.adapter.MartAdapter

import com.example.ecoharvest.databinding.ActivityMartBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class MartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMartBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter : MartAdapter
    private var martList = ArrayList<Mart>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        getData()


    }

    private fun initVars() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        martList = ArrayList()
        adapter = MartAdapter(martList)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("data")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
                    for (doc in value?.documentChanges!!) {
                        if (doc.type == DocumentChange.Type.ADDED) {
                            martList.add(doc.document.toObject(Mart::class.java))
                        }
                    }
                    adapter.notifyDataSetChanged()
            }
    }
}