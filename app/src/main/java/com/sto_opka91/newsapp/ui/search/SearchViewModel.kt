package com.sto_opka91.newsapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sto_opka91.newsapp.data.api.NewsRepository
import com.sto_opka91.newsapp.models.NewsResponse
import com.sto_opka91.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {
    val searchNewLiveData:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    init {
        getSearchNews("")
    }
     fun getSearchNews (query: String)=
        viewModelScope.launch {
            searchNewLiveData.postValue(Resource.Loading())
            val responce= repository.getSearchNews(query = query, pageNumber = searchNewsPage)
            if(responce.isSuccessful){
                responce.body().let { res ->
                    searchNewLiveData.postValue(Resource.Success(res))
                }
            }else{
                searchNewLiveData.postValue(Resource.Error(message = responce.message()))
            }
        }
}