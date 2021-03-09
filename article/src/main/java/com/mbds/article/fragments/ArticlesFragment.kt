package com.mbds.article.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.article.MainActivity
import com.mbds.article.R
import com.mbds.article.adapters.ArticleAdapter
import com.mbds.article.changeFragment
import com.mbds.article.databinding.FragmentArticlesBinding

import com.mbds.article.model.Article
import com.mbds.article.model.ArticleInfo
import com.mbds.article.repositories.Articlepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticlesFragment : Fragment() {
    private val repository = Articlepository()

    private lateinit var myView: View
    private lateinit var categorie: String

    lateinit var binding: FragmentArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setTitle("Actualités : " + categorie.get(0).toUpperCase() + categorie.substring(1,categorie.length))
        myView = view
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val articles = mutableListOf<Article>()
        val adapterRecycler = ArticleAdapter(articles)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launch {
            getData()
        }
    }

    //S'execute dans un thread secondaire
    private suspend fun getData() {
        var result: List<Article>
        withContext(Dispatchers.IO) {
            try {
                if(categorie == "error"){
                    result = repository.listError(categorie)!!
                }
                else{
                    result = repository.list(categorie)!!
                }
            } catch (e: Exception) {
                result = listOf<Article>()
                println(e.message)
                (activity as? MainActivity)?.changeFragment(CategoriesFragment.newInstance())
                activity?.runOnUiThread{
                    println(e.message)
                    when (e.message.toString().toInt()) {
                        400 -> Toast.makeText(
                            context,
                            "Bad Request. The request was unacceptable, often due to a missing or misconfigured parameter.",
                            Toast.LENGTH_SHORT
                        ).show()
                        401 -> Toast.makeText(
                            context,
                            "Unauthorized. Your API key was missing from the request, or wasn't correct.",
                            Toast.LENGTH_SHORT
                        ).show()
                        404 -> Toast.makeText(
                            context,
                            "Not Found.",
                            Toast.LENGTH_SHORT
                        ).show()
                        429 -> Toast.makeText(
                            context,
                            "Too Many Requests. You made too many requests within a window of time and have been rate limited. Back off for a while.",
                            Toast.LENGTH_SHORT
                        ).show()
                        500 -> Toast.makeText(
                            context,
                            "Server Error. Something went wrong on our side.",
                            Toast.LENGTH_SHORT
                        ).show()
                        else -> Toast.makeText(
                            context,
                            "Error in request.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
            bindData(result)
        }
    }

    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            //afficher les données dans le recycler
            val recyclerView: RecyclerView = myView.findViewById(R.id.recycler_view)
            val adapterRecycler = ArticleAdapter(result)
            recyclerView.adapter = adapterRecycler
        }
    }

    companion object {
        fun newInstance(categorie: String): ArticlesFragment {
            return ArticlesFragment().apply {
                this.categorie = categorie
            }
        }
    }
}

private fun Lifecycle.launch(function: () -> Unit) {

}
