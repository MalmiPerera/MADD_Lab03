package com.example.calculator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab03.R

class CalculatorFragment : Fragment() {

    interface OnCalculationListener {
        fun onCalculationResult(result: Double, operation: String)
    }

    private var listener: OnCalculationListener? = null
    private lateinit var editNumber1: EditText
    private lateinit var editNumber2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCalculationListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnCalculationListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        editNumber1 = view.findViewById(R.id.editNumber1)
        editNumber2 = view.findViewById(R.id.editNumber2)
        btnAdd = view.findViewById(R.id.btnAdd)
        btnSubtract = view.findViewById(R.id.btnSubtract)
        btnMultiply = view.findViewById(R.id.btnMultiply)
        btnDivide = view.findViewById(R.id.btnDivide)

        // Set click listeners
        btnAdd.setOnClickListener { performCalculation("+") }
        btnSubtract.setOnClickListener { performCalculation("-") }
        btnMultiply.setOnClickListener { performCalculation("×") }
        btnDivide.setOnClickListener { performCalculation("÷") }
    }

    private fun performCalculation(operation: String) {
        val num1Str = editNumber1.text.toString().trim()
        val num2Str = editNumber2.text.toString().trim()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(context, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val num1 = num1Str.toDouble()
            val num2 = num2Str.toDouble()

            val result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "×" -> num1 * num2
                "÷" -> {
                    if (num2 == 0.0) {
                        Toast.makeText(context, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return
                    }
                    num1 / num2
                }
                else -> 0.0
            }

            val operationText = "$num1 $operation $num2"
            listener?.onCalculationResult(result, operationText)

        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}