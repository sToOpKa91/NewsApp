package com.sto_opka91.newsapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sto_opka91.newsapp.data.api.NewsRepository
import com.sto_opka91.newsapp.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    init{
        getSavedArticles()
    }
    fun getSavedArticles() = viewModelScope.launch(Dispatchers.IO){
        val res = repository.getFavoriteArticles()
        println("DB size ${res.size}")
            repository.getFavoriteArticles()
    }
    fun saveFavoriteArticles(article: Article) = viewModelScope.launch(Dispatchers.IO){
        repository.addToFavorite(article = article)
    }
}