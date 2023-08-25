package com.sto_opka91.newsapp.ui.favorite

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sto_opka91.newsapp.R
import com.sto_opka91.newsapp.databinding.FragmentFavoriteBinding
import com.sto_opka91.newsapp.ui.details.DetailsFragment


class FavoriteFragment : Fragment() {
private lateinit var binding: FragmentFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    companion object {

        fun newInstance() = FavoriteFragment()
    }
}