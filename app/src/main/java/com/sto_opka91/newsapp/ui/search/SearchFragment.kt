package com.sto_opka91.newsapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sto_opka91.newsapp.databinding.FragmentSearchBinding
import com.sto_opka91.newsapp.ui.adapters.NewsAdapter
import com.sto_opka91.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
private lateinit var binding: FragmentSearchBinding
lateinit var newsAdapter: NewsAdapter
private val viewModel by viewModels<SearchViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        var job:Job? = null
        binding.edTSearch.addTextChangedListener { text: Editable? ->
            job?.cancel()
            job = MainScope().launch {
                delay(500)
                text?.let{
                    if(it.toString().isNotEmpty()){
                        viewModel.getSearchNews(query = it.toString())
                    }
                }
            }
        }

        viewModel.searchNewLiveData.observe(viewLifecycleOwner){responce ->
            when(responce){
                is Resource.Success ->{
                    binding.pBSearch.visibility = View.INVISIBLE
                    responce.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error ->{
                    binding.pBSearch.visibility = View.INVISIBLE
                    responce.data?.let{
                        Log.e("checkData", "mainFragment Error : ${it}")
                    }
                }
                is Resource.Loading ->{
                    binding.pBSearch.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        binding.rvSearch.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
