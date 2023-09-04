package com.sto_opka91.newsapp.data.db


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sto_opka91.newsapp.models.Article
@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(article: Article)

    @Delete
    fun delete(article: Article)
}