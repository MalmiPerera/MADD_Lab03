package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.lab03.R

class ainActivity : AppCompatActivity(), CalculatorFragment.OnCalculationListener {

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager

        // Load the calculator fragment
        if (savedInstanceState == null) {
            val calculatorFragment = CalculatorFragment()
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container, calculatorFragment)
                .commit()
        }
    }

    override fun onCalculationResult(result: Double, operation: String) {
        // Load result fragment
        val resultFragment = ResultFragment.newInstance(result, operation)
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, resultFragment)
            .addToBackStack(null)
            .commit()
    }
}