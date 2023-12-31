package com.sto_opka91.newsapp.data.api

import com.sto_opka91.newsapp.models.NewsResponse
import com.sto_opka91.newsapp.utils.Constans.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query : String,
        @Query("page") page : Int = 1,
        @Query("apiKey") apiKey : String = API_KEY
    ):Response<NewsResponse>
    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") countryCode : String = "ru",
        @Query("page") page : Int = 1,
        @Query("apiKey") apiKey : String = API_KEY
    ):Response<NewsResponse>
}