package com.sto_opka91.newsapp.ui.main


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
class MainViewModel @Inject constructor(private val repository : NewsRepository): ViewModel(){
    val newsLiveData : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsPage = 1

    init{
        getNews("us")
    }
    private fun getNews(countryCode: String)=
        viewModelScope.launch {
            newsLiveData.postValue(Resource.Loading())
            val responce= repository.getNews(countryCode = countryCode, pageNumber = newsPage)
            if(responce.isSuccessful){
                responce.body().let { res ->
                    newsLiveData.postValue(Resource.Success(res))
                }
            }else{
                newsLiveData.postValue(Resource.Error(message = responce.message()))
            }
        }
}