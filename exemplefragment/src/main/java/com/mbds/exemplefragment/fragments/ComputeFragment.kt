package com.mbds.exemplefragment.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.mbds.exemplefragment.R
import com.mbds.exemplefragment.databinding.ComputationFragmentBinding

class ComputationFragment : Fragment(), TextWatcher {

    private lateinit var binding: ComputationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ComputationFragmentBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.computation_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.field1.addTextChangedListener()
        binding.buttonCustom.setOnClickListener {
            binding.tView1.text = (binding.field1.getNumber() + binding.field2.getNumber()).toString()

        }
    }

    private fun EditText.getNumber() = text.toString().toDouble()

    override fun afterTextChanged(s: Editable?) {
        val isEnabled: Boolean = binding.buttonCustom.isEnabled = binding.field1.isNotNullOrBlank() == true && binding.field2.isNotNullOrBlank()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    private fun EditText.isNotNullOrBlank() = this != null && text.isNotBlank()

    private fun compute(checkedId: Int) {
    val result: String = when(checkedId){
        R.id.add -> "${binding.field1.getNumber().plus(binding.field2.getNumber())}"
        R.id.minus -> "${binding.field1.getNumber().minus(binding.field2.getNumber())}"
        R.id.div -> "${binding.field1.getNumber().div(binding.field2.getNumber())}"
        R.id.mult -> "${binding.field1.getNumber().times(binding.field2.getNumber())}"
        else -> "${binding.field1.getNumber().plus(binding.field2.getNumber())}"

    }
        binding.computeResult.text = result
    }
}