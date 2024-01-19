package com.example.ecoharvest.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecoharvest.databinding.ItemMartBinding

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



