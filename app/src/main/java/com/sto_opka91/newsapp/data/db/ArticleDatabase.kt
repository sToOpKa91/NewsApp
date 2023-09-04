package com.sto_opka91.newsapp.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.sto_opka91.newsapp.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = true)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao


}