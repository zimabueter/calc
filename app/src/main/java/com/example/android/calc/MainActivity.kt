package com.example.android.calc

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var squareRoot: Button
    private lateinit var plusMinus: Button
    private lateinit var percent: Button
    private var operand: Double = 0.0
    private lateinit var resultText: TextView
    private var operation: String = " "
    private lateinit var equalsButton: Button
    private lateinit var clear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusMinus = findViewById(R.id.plusMinus)
        squareRoot = findViewById(R.id.squareRoot)
        resultText = findViewById(R.id.resultText)
        equalsButton = findViewById(R.id.equalsButton)
        percent = findViewById(R.id.percent)
        clear = findViewById(R.id.clear_text)

        clear.setOnClickListener {
            resultText.text = resultText.text.toString().dropLast(1)
        }
        clear.setOnLongClickListener {
            resultText.text = ""
            return@setOnLongClickListener true

        }
        squareRoot.setOnClickListener {
            resultText.text = sqrt(resultText.text.toString().toDouble()).toString()
        }

        plusMinus.setOnClickListener {
            resultText.text = resultText.text.toString().replaceRange(0, 0, "-")


        }

        percent.setOnClickListener {
            val secOperandText = resultText.text.toString()
            var secOperand = 0.0
            if (secOperandText.isNotEmpty()) {
                secOperand = secOperandText.toDouble()
            }

            when (operation) {
                "+" -> resultText.text = ((operand + secOperand) / 100).toString()
                "-" -> resultText.text = ((operand - secOperand) / 100).toString()
                "*" -> resultText.text = ((operand * secOperand) / 100).toString()
                "/" -> resultText.text = ((operand / secOperand) / 100).toString()
            }
        }
        equalsButton.setOnClickListener {
            val secOperandText = resultText.text.toString()
            var secOperand = 0.0
            if (secOperandText.isNotEmpty()) {
                secOperand = secOperandText.toDouble()
            }

            when (operation) {
                "+" -> resultText.text = (operand + secOperand).toString()
                "-" -> resultText.text = (operand - secOperand).toString()
                "*" -> resultText.text = (operand * secOperand).toString()
                "/" -> resultText.text = (operand / secOperand).toString()
         
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun numberClick(view: View) {
        if (view is Button) {

            val number: String = view.text.toString()
            var result: String = resultText.text.toString()
            if (result == "0") {
                result = ""
            }
            resultText.text = result + number

        }
    }

    fun operationClick(view: View) {
        if (view is Button) {
            if (resultText.text.isNotEmpty()) {
                operand = resultText.text.toString().toDouble()
            }
            resultText.text = ""
            operation = view.text.toString()
        }
    }

}

