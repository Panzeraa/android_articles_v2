package com.mbds.exemplefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mbds.exemplefragment.fragments.ComputationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment()
    }

    /**
     * Ajouter le fragment [ComputationFragment] dans l'activité
     */
    private fun changeFragment() {
        //1. On créé une instance du fragment
        val fragment = ComputationFragment()
        //2. On crée une transactoin sur le fragment manager
        supportFragmentManager.beginTransaction().apply {
        //3. On remplace le contenu du container
            replace(R.id.fragment_container, fragment)
        //4. on ajoute la transaction dans le backstack
            addToBackStack(null)
        }.commit()
        //5. On commit la transaction
    }
}