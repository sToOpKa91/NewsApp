package com.sto_opka91.newsapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sto_opka91.newsapp.R
import com.sto_opka91.newsapp.databinding.FragmentMainBinding
import com.sto_opka91.newsapp.ui.adapters.NewsAdapter
import com.sto_opka91.newsapp.ui.favorite.FavoriteFragment
import com.sto_opka91.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

private lateinit var binding: FragmentMainBinding
private val viewModel by viewModels<MainViewModel>()
    lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.newsLiveData.observe(viewLifecycleOwner){responce ->
            when(responce){
                is Resource.Success ->{
                    binding.pbMain.visibility = View.INVISIBLE
                    responce.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error ->{
                    binding.pbMain.visibility = View.INVISIBLE
                    responce.data?.let{
                        Log.e("checkData", "mainFragment Error : ${it}")
                    }
                }
                is Resource.Loading ->{
                    binding.pbMain.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}