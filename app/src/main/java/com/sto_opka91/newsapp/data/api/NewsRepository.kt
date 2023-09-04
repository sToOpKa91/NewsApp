package com.sto_opka91.newsapp.data.api

import com.sto_opka91.newsapp.data.db.ArticleDao
import com.sto_opka91.newsapp.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val articleDao: ArticleDao) {
    suspend fun getNews(countryCode : String, pageNumber: Int) =
        newsService.getHeadlines(countryCode = countryCode, page = pageNumber)
    suspend fun getSearchNews(query: String, pageNumber: Int) =
        newsService.getEverything(query = query, page = pageNumber)
    fun getFavoriteArticles() = articleDao.getAllArticles()
    fun addToFavorite(article: Article) = articleDao.insert(article = article)
     fun deleteToFavorite(article: Article) {
        articleDao.delete(article = article)
    }

}