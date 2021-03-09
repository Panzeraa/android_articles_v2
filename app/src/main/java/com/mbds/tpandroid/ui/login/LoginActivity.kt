package com.mbds.tpandroid.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.mbds.tpandroid.MainActivity

import com.mbds.tpandroid.R
import com.mbds.tpandroid.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var mailInput : EditText
    lateinit var passwordInput : EditText
    lateinit var binding : ActivityLoginBinding
//    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(baseContext))
//        setContentView(R.layout.activity_login)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews(){
        loginButton = findViewById(R.id.login)
        mailInput = findViewById(R.id.username)
        passwordInput = findViewById(R.id.password)
//        loginButton.isEnabled = true
        loginButton.setOnClickListener(){
            if(!mailInput.text.isNullOrBlank() && !passwordInput.text.isNullOrBlank()) {
                val intent = Intent(baseContext, MainActivity::class.java)

                startActivity(intent)
            }
            else{
                Toast.makeText(baseContext, "Erreur", Toast.LENGTH_SHORT).show()

            }
        }
    }
    override fun onResume() {
        super.onResume()
//        loginButton.isEnabled = false
    }

}