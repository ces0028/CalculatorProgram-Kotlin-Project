package kr.or.mrhi.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kr.or.mrhi.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var tvCalculate : TextView
    lateinit var tvResult : TextView
    var number1 = ""
    var number2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        tvCalculate = binding.tvCalculate
        tvResult = binding.tvResult
        setContentView(binding.root)
    }

    fun numberButtonClicked(view: View){
        when(view.id) {
            R.id.btnNumber0 -> deleteZero("0")
            R.id.btnNumber1 -> deleteZero("1")
            R.id.btnNumber2 -> deleteZero("2")
            R.id.btnNumber3 -> deleteZero("3")
            R.id.btnNumber4 -> deleteZero("4")
            R.id.btnNumber5 -> deleteZero("5")
            R.id.btnNumber6 -> deleteZero("6")
            R.id.btnNumber7 -> deleteZero("7")
            R.id.btnNumber8 -> deleteZero("8")
            R.id.btnNumber9 -> deleteZero("9")
        }
        defineNumber()
        calculate()
    }

    fun arithmeticButtonClicked(view: View) {
        deletePoint()
        when(view.id) {
            R.id.btnPlus -> inputSymbol("+")
            R.id.btnMinus -> inputSymbol("-")
            R.id.btnMultiplication -> inputSymbol("X")
            R.id.btnDivision -> inputSymbol("÷")
        }
    }

    fun otherButtonClicked(view: View) {
        when (view.id) {
            R.id.btnAllClear -> {
                tvCalculate.setText("")
                tvResult.setText("")
            }
            R.id.btnClear -> {
                binding.tvCalculate.text = binding.tvCalculate.text.dropLast(1)
                defineNumber()
                calculate()
                if (number2 == "") {
                    tvResult.setText("")
                }
            }
            R.id.btnPoint -> inputPoint()
            R.id.btnEquals -> {
                deletePoint()
                binding.tvCalculate.text = binding.tvResult.text.toString()
                binding.tvResult.setText("")
            }
        }
    }

    fun inputPoint() {
        if (binding.tvCalculate.text.length < 1) {
            binding.tvCalculate.setText("0.")
        } else if(binding.tvCalculate.text.endsWith("+")) {
            binding.tvCalculate.text = "${binding.tvCalculate.text.toString()}0."
        } else if(binding.tvCalculate.text.endsWith("-")) {
            binding.tvCalculate.text = "${binding.tvCalculate.text.toString()}0."
        } else if(binding.tvCalculate.text.endsWith("X")) {
            binding.tvCalculate.text = "${binding.tvCalculate.text.toString()}0."
        } else if(binding.tvCalculate.text.endsWith("÷")) {
            binding.tvCalculate.text = "${binding.tvCalculate.text.toString()}0."
        } else {
            binding.tvCalculate.text = "${binding.tvCalculate.text.toString()}."
        }
    }

    fun inputSymbol(symbol: String) {
        if(binding.tvCalculate.text.toString() == "") {
            Toast.makeText(this, "먼저 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
        } else {
            binding.tvCalculate.text = "${binding.tvCalculate.text.toString()}$symbol"
        }
    }

    fun deletePoint() {
        if(binding.tvCalculate.text.endsWith(".")) {
            binding.tvCalculate.setText("${binding.tvCalculate.text.toString()}0")
        }
    }

    fun defineNumber() {
        if (binding.tvCalculate.text.toString().contains( "+")) {
            number1 = binding.tvCalculate.text.toString().substring(0, binding.tvCalculate.text.toString().indexOf("+"))
            number2 = binding.tvCalculate.text.toString().substring(binding.tvCalculate.text.toString().indexOf("+") + 1)
        }  else if (binding.tvCalculate.text.toString().contains( "-")) {
            number1 = binding.tvCalculate.text.toString().substring(0, binding.tvCalculate.text.toString().indexOf("-"))
            number2 = binding.tvCalculate.text.toString().substring(binding.tvCalculate.text.toString().indexOf("-") + 1)
        } else if (binding.tvCalculate.text.toString().contains( "X")) {
            number1 = binding.tvCalculate.text.toString().substring(0, binding.tvCalculate.text.toString().indexOf("X"))
            number2 = binding.tvCalculate.text.toString().substring(binding.tvCalculate.text.toString().indexOf("X") + 1)
        } else if(binding.tvCalculate.text.toString().contains( "÷")) {
            number1 = binding.tvCalculate.text.toString().substring(0, binding.tvCalculate.text.toString().indexOf("÷"))
            number2 = binding.tvCalculate.text.toString().substring(binding.tvCalculate.text.toString().indexOf("÷") + 1)
        }
    }

    fun calculate() {
        if(binding.tvCalculate.text.toString() != "" && number2 != "") {
            if (binding.tvCalculate.text.toString().contains("+")) {
                if (binding.tvCalculate.text.toString().contains(".")) {
                    binding.tvResult.text = (number1.toDouble() + number2.toDouble()).toString()
                } else {
                    binding.tvResult.text = (number1.toInt() + number2.toInt()).toString()
                }
            } else if (binding.tvCalculate.text.toString().contains("-")) {
                if (binding.tvCalculate.text.toString().contains(".")) {
                    binding.tvResult.text = (number1.toDouble() - number2.toDouble()).toString()
                } else {
                    binding.tvResult.text = (number1.toInt() - number2.toInt()).toString()
                }
            } else if (binding.tvCalculate.text.toString().contains("X")) {
                if (binding.tvCalculate.text.toString().contains(".")) {
                    binding.tvResult.text = (number1.toDouble() * number2.toDouble()).toString()
                } else {
                    binding.tvResult.text = (number1.toInt() * number2.toInt()).toString()
                }
            } else if (binding.tvCalculate.text.toString().contains("÷")) {
                if ((number1.toDouble() % number2.toDouble()).equals(0)) {
                    binding.tvResult.text = (number1.toInt() / number2.toInt()).toString()
                } else {
                    binding.tvResult.text = (number1.toDouble() / number2.toDouble()).toString()
                }
            }
        }
    }

    fun deleteZero(number: String) {
        if(binding.tvCalculate.text.toString() == "0" || number2 == "0") {
            binding.tvCalculate.text = binding.tvCalculate.text.dropLast(1)
        }
        binding.tvCalculate.text = "${binding.tvCalculate.text.toString()}$number"
    }
}