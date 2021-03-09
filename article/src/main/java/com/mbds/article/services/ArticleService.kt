package com.mbds.article.services

import com.mbds.article.model.ArticleInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("v2/everything?apiKey=79e9f5affe334dadbf8b3e91d382bcb7")
    fun list(@Query("q") categorie: String): Call<ArticleInfo>

    @GET("v8/everything?apiKey=79e9f5affe334dadbf8b3e91d382bcb7")
    fun listError(@Query("q") categorie: String): Call<ArticleInfo>
}