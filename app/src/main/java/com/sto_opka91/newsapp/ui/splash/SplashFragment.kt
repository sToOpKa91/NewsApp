package com.sto_opka91.newsapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sto_opka91.newsapp.R
import com.sto_opka91.newsapp.databinding.FragmentSplashBinding
import com.sto_opka91.newsapp.ui.search.SearchFragment


class SplashFragment : Fragment() {
private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    companion object {

        fun newInstance() = SplashFragment()
    }
}