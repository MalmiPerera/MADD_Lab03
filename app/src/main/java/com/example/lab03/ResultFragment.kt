package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab03.R

class ResultFragment : Fragment() {

    companion object {
        private const val ARG_RESULT = "result"
        private const val ARG_OPERATION = "operation"

        fun newInstance(result: Double, operation: String): ResultFragment {
            return ResultFragment().apply {
                arguments = Bundle().apply {
                    putDouble(ARG_RESULT, result)
                    putString(ARG_OPERATION, operation)
                }
            }
        }
    }

    private var result: Double = 0.0
    private var operation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            result = it.getDouble(ARG_RESULT)
            operation = it.getString(ARG_OPERATION) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvOperation: TextView = view.findViewById(R.id.tvOperation)
        val tvResult: TextView = view.findViewById(R.id.tvResult)
        val btnBack: Button = view.findViewById(R.id.btnBack)

        tvOperation.text = operation

        // Format result to avoid unnecessary decimals
        val resultText = if (result == result.toLong().toDouble()) {
            result.toLong().toString()
        } else {
            result.toString()
        }
        tvResult.text = "Answer: $resultText"

        btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}