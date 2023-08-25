package com.sto_opka91.newsapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sto_opka91.newsapp.R
import com.sto_opka91.newsapp.databinding.FragmentDetailsBinding



class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    companion object {

        fun newInstance() = DetailsFragment()
    }

}