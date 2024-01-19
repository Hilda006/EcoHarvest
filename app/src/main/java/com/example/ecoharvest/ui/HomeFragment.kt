package com.example.ecoharvest.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecoharvest.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
  private lateinit var binding : FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ecoMart.setOnClickListener {
            startActivity(Intent(context, MartActivity::class.java))
        }
        binding.ecoCollab.setOnClickListener {
            startActivity(Intent(context, CollaborationActivity::class.java))
        }
        binding.ecoTips.setOnClickListener {
            startActivity(Intent(context, TipsActivity::class.java))
        }
        binding.EcoEdu.setOnClickListener {
            startActivity(Intent(context, EducationActivity::class.java))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
            }
    }
}