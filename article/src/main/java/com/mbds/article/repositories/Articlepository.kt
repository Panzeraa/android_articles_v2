package com.mbds.article.repositories

import com.mbds.article.model.Article
import com.mbds.article.services.ArticleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class Articlepository {
    private val service: ArticleService
    init {
        val baseUrl = "https://newsapi.org/"
//        val url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=79e9f5affe334dadbf8b3e91d382bcb7/"
        val retrofit = Retrofit.Builder().apply {
            baseUrl(URL(baseUrl))
        }.addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ArticleService::class.java)
    }

    fun list(categorie: String): List<Article>? {
        val response = service.list(categorie).execute()
        println(response.code())
        if(response.code() != 200){
            throw Exception(response.code().toString())
        }
        return response.body()?.articles
    }

    fun listError(categorie: String): List<Article>? {
        val response = service.listError(categorie).execute()
        println(response.code())
        if(response.code() != 200){
            throw Exception(response.code().toString())
        }
        return response.body()?.articles
    }
}