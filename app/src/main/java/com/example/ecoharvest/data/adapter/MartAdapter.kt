package com.example.ecoharvest.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecoharvest.DetailMartActivity
import com.example.ecoharvest.MartActivity
import com.example.ecoharvest.databinding.ItemMartBinding
import com.example.ecoharvest.utils.uriToFile
import java.io.File

class MartAdapter(private val mart : List<Mart>) : RecyclerView.Adapter<MartAdapter.MyViewHolder>() {

    class MyViewHolder(val binding : ItemMartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mart.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.itemView.context
        val mart = mart[position]

        holder.binding.apply {
//            nameProduct.text = mart.name
//            location.text = mart.location

            Glide.with(context)
                .load(mart.image)
                .into(imgProduct)

            }

        }
    }



