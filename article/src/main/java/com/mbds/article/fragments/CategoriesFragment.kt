package com.mbds.article.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.article.MainActivity
import com.mbds.article.R
import com.mbds.article.adapters.CategoriAdapter
import com.mbds.article.changeFragment
import com.mbds.article.databinding.FragmentCategoriesBinding
import com.mbds.article.model.Category

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment(), CategoriAdapter.OnCategorieListener {

    lateinit var binding: FragmentCategoriesBinding
    lateinit var articles: List<Category>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setTitle("Actualités")
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        articles = listOf(
                Category(name = "politique", description = "Voir les articles liés à la catégorie politique", image = "https://lumieresdelaville.net/wp-content/uploads/2017/11/7392285_d60a92a8-c940-11e7-b36b-53a09aedfc0c-1_1000x625.jpg"),
                Category(name = "économie", description = "Voir les articles liés à la catégorie économie", image = "https://images.niooz.fr/safe_image.php?clean=1&width=600&i=/cache/mediaid/3/3/7/0/4/3370420.jpg"),
                Category(name = "éducation", description = "Voir les articles liés à la catégorie politique", image = "https://www.weka.fr/actualite/wp-content/uploads/2020/10/le-ministere-de-l-education-nationale-lance-les-territoires-numeriques-educatifs-640x312.jpg"),
                Category(name = "pandémie", description = "Voir les articles liés à la catégorie pandémie", image = "https://resize1.prod.docfr.doc-media.fr/rcrop/450,340,center-middle/img/var/doctissimo/storage/images/fr/www/sante/news/coronavirus-covid19-pourquoi-plus-infectieux-que-les-autres-2003/8418826-1-fre-FR/coronavirus-covid19-pourquoi-plus-infectieux-que-les-autres-2003.jpg"),
                Category(name = "scientifique", description = "Voir les articles liés à la catégorie scientifique", image = "https://www.ican-institute.org/wp-content/uploads/2019/04/dna-3539309-pixabay.jpg"),
                Category(name = "biologie", description = "Voir les articles liés à la catégorie biologie", image = "https://lh3.googleusercontent.com/proxy/jA9NfwZoARUUqSydi7Y9v9nx_9rBLW0NBlpjCjIEXK09UOf40NVOe42XK3s2yb2S_AE_ZVS9fBMzuuHoaUwkrho7JlfwU8STnTbNXg623FtMp7QHw-q-I0rf"),
                Category(name = "error", description = "Catégorie error pour tester une erreur 404", image = "https://picsum.photos/500/300")
        )
        val adapterRecycler = CategoriAdapter(articles, this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler
    }

    override fun onCategorieClick(position: Int) {
        val categorySelected = articles.get(position).name
        (activity as? MainActivity)?.changeFragment(
            ArticlesFragment.newInstance(
                categorySelected
            )
        )
    }

    companion object {
        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }

}