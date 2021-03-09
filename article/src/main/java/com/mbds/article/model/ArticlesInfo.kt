package com.mbds.article.model

import java.util.*

data class ArticleInfo(val status: String, val totalResults: Int, val articles: List<Article>)