package com.mbds.calculatrice

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mbds.calculatrice.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var resultat: Double = 0.0
    var operation: String = "+"
    var nombre = "0"
    var finCalcul = false
    var operationTemp = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(baseContext))
        setContentView(binding.root)
        initViews()
    }


    private fun initViews() {
        binding.zero.setOnClickListener() {
            buttonListener(binding.zero)
        }
        binding.un.setOnClickListener() {
            buttonListener(binding.un)
        }
        binding.deux.setOnClickListener() {
            buttonListener(binding.deux)
        }
        binding.trois.setOnClickListener() {
            buttonListener(binding.trois)
        }
        binding.quatre.setOnClickListener() {
            buttonListener(binding.quatre)
        }
        binding.cinq.setOnClickListener() {
            buttonListener(binding.cinq)
        }
        binding.six.setOnClickListener() {
            buttonListener(binding.six)
        }
        binding.sept.setOnClickListener() {
            buttonListener(binding.sept)
        }
        binding.huit.setOnClickListener() {
            buttonListener(binding.huit)
        }
        binding.neuf.setOnClickListener() {
            buttonListener(binding.neuf)
        }
        binding.multiplie.setOnClickListener() {
            buttonListener(binding.multiplie)
        }
        binding.plus.setOnClickListener() {
            buttonListener(binding.plus)
        }
        binding.divise.setOnClickListener() {
            buttonListener(binding.divise)
        }
        binding.moins.setOnClickListener() {
            buttonListener(binding.moins)
        }
        binding.egal.setOnClickListener() {
            buttonListenerEgal()
        }
    }

    private fun buttonListenerEgal() {
        ope(operation)
        binding.expression.append(" = ")
        finCalcul = true
    }

    private fun buttonListener(EditView: TextView) {
        if (finCalcul) {
            binding.expression.text = ""
            binding.resultat.text = ""
            nombre = "0"
            operation = "+"
            resultat = 0.0
            finCalcul = false
        }

        when (EditView.text) {
            "+" -> {
                checkNombre(EditView.text.toString())
                operation = "+"
            }
            "-" -> {
                checkNombre(EditView.text.toString())
                operation = "-"
            }
            "*" -> {
                checkNombre(EditView.text.toString())
                operation = "*"
            }
            "/" -> {
                checkNombre(EditView.text.toString())
                operation = "/"
            }
            else -> {
                nombre += EditView.text
                binding.expression.append(EditView.text)
            }
        }
    }

    private fun checkNombre(txt: String){
        if (nombre != "") {
            binding.expression.append(txt)
            ope(operation)
        } else {
            binding.expression.text = binding.expression.text.dropLast(1)
            binding.expression.append(txt)
        }
    }

    private fun ope(oper: String) {
        when (oper) {
            "+" -> resultat += nombre.toDouble()
            "-" -> resultat -= nombre.toDouble()
            "/" -> resultat /= nombre.toDouble()
            "*" -> resultat *= nombre.toDouble()
        }
        nombre = ""
        binding.resultat.text = resultat.toString()
    }


    override fun onResume() {
        super.onResume()
    }

}

