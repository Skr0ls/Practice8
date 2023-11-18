package ru.mggtk.practice8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var currentInput = StringBuilder()
    private var currentOperator: String? = null
    private var firstNumber: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("Calc")
        display = findViewById(R.id.textView)

        val button0 = findViewById<Button>(R.id.Button0)
        button0.setOnClickListener { appendCharacter("0") }

        val button1 = findViewById<Button>(R.id.Button1)
        button1.setOnClickListener { appendCharacter("1") }

        val button2 = findViewById<Button>(R.id.Button2)
        button2.setOnClickListener { appendCharacter("2") }

        val button3 = findViewById<Button>(R.id.Button3)
        button3.setOnClickListener { appendCharacter("3") }

        val button4 = findViewById<Button>(R.id.Button4)
        button4.setOnClickListener { appendCharacter("4") }

        val button5 = findViewById<Button>(R.id.Button5)
        button5.setOnClickListener { appendCharacter("5") }

        val button6 = findViewById<Button>(R.id.Button6)
        button6.setOnClickListener { appendCharacter("6") }

        val button7 = findViewById<Button>(R.id.Button7)
        button7.setOnClickListener { appendCharacter("7") }

        val button8 = findViewById<Button>(R.id.Button8)
        button8.setOnClickListener { appendCharacter("8") }

        val button9 = findViewById<Button>(R.id.Button9)
        button9.setOnClickListener { appendCharacter("9") }

        val buttonPlus = findViewById<Button>(R.id.ButtonPlus)
        buttonPlus.setOnClickListener { handleOperator("+") }

        val buttonMultiplication = findViewById<Button>(R.id.ButtonMultiplication)
        buttonMultiplication.setOnClickListener { handleOperator("*") }

        val buttonMinus = findViewById<Button>(R.id.ButtonMinus)
        buttonMinus.setOnClickListener { handleOperator("-") }

        val slesh = findViewById<Button>(R.id.Divide)
        slesh.setOnClickListener { handleOperator("/") }

        val buttonEqual = findViewById<Button>(R.id.ButtonEqual)
        buttonEqual.setOnClickListener {
            if (currentOperator != null) {
                try {
                    val secondNumber = currentInput.toString().toDouble()
                    val result = when (currentOperator) {
                        "+" -> firstNumber!! + secondNumber
                        "-" -> firstNumber!! - secondNumber
                        "*" -> firstNumber!! * secondNumber
                        "/" -> {
                            if (secondNumber == 0.0 || secondNumber.toInt() == 0 ) {
                                display.text = "Dived by zero"
                                return@setOnClickListener
                            }
                            if (firstNumber == 0.0 ) {
                                display.text = "1.0"
                                return@setOnClickListener
                            } else{firstNumber!! / secondNumber}

                        }
                        else -> firstNumber
                    }
                    display.text = result.toString()
                    firstNumber = result
                    currentOperator = null
                    currentInput = StringBuilder()
                } catch (e: NumberFormatException) {
                    display.text = "Error"
                }
            }
        }

        val buttonClear = findViewById<Button>(R.id.C)
        buttonClear.setOnClickListener {
            currentInput = StringBuilder()
            currentOperator = null
            firstNumber = null
            display.text = "0.0"
        }
    }

    private fun appendCharacter(character: String) {
        currentInput.append(character)
        display.text = currentInput.toString()
    }

    private fun handleOperator(operator: String) {
        try {
            if (currentOperator == null) {
                firstNumber = currentInput.toString().toDouble()
                currentInput = StringBuilder()
            } else {
                val secondNumber = currentInput.toString().toDouble()
                val result = when (currentOperator) {
                    "+" -> firstNumber!! + secondNumber
                    "-" -> firstNumber!! - secondNumber
                    "*" -> firstNumber!! * secondNumber
                    "/" -> {
                        if (secondNumber == 0.0 || secondNumber.toInt() == 0) {
                            display.text = "Деление на 0"
                            return
                        }
                        if (firstNumber == 0.0 ) {
                            display.text = "1.0"
                            return
                        } else{firstNumber!! / secondNumber}
                    }
                    else -> firstNumber
                }
                display.text = result.toString()
                firstNumber = result
                currentInput = StringBuilder()
            }
            currentOperator = operator
        } catch (e: NumberFormatException) {
            display.text = "Error"
        }
    }
}