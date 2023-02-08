package com.example.calcapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput : TextView? = null
    var lastNum = false
    var lastDot = false


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.textView1)
    }
    fun onDigit(view : View){
        tvInput?.append((view as Button).text)
        lastNum = true
    }
    fun onClear(view: View){
        tvInput?.text = ""
        lastDot= false
        lastNum = false
    }

    fun decimalPnt(view: View){
        if (lastNum && !lastDot){
            tvInput?.append(".")
            lastDot= true

        }
    }

    fun isOperatorAdded(value: String) : Boolean{

        return if ( value.substring(1).contains("-")){
            true

        }
        else if (value.startsWith("-")){
            false   //for negative values no error
        }else {
            value.contains("/") || value.contains("+") || value.contains("*") // if one of them is used it'll return boolean value
// contains returns a boolean value

        }
    }
    fun minusOnOperator(view: View){
        tvInput?.text?.let{
            if(it.toString() == ""){
                tvInput?.append((view as Button).text)
            }
            if (lastNum && !isOperatorAdded(it.toString())){ // it : lambda for text(charSequence) on our tvInput
                tvInput?.append((view as Button).text)
                lastDot = false
                lastNum = false

            }
        }

    }
    fun onOperator (view: View){

        tvInput?.text?.let{

            if (lastNum && !isOperatorAdded(it.toString())){ // it : lambda for text(charSequence) on our tvInput
                tvInput?.append((view as Button).text)
                lastDot = false
                lastNum = false



            }
        }
    }
    fun onEquals(view: View){
        if(lastNum){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try { // this will be used  when string is started with - as - is a delimiter
                    if(tvValue.startsWith("-")){
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }
                    if (tvValue.contains("-")){
                        val splitValue = tvValue.split("-") // string method
                        // for ex 44 - 78
                        var one = splitValue[0] // 44
                        var two = splitValue[1] // 78
                        if(prefix.isNotEmpty()){
                            one = prefix + one //adding - sign again to the number
                        }

                        tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                    }
                else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+") // string method
                    // for ex 44 + 78
                    var one = splitValue[0] // 44
                    var two = splitValue[1] // 78
                    if(prefix.isNotEmpty()){
                        one = prefix + one //adding - sign again to the number
                    }

                    tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                }
                else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*") // string method
                    // for ex 44 * 78
                    var one = splitValue[0] // 44
                    var two = splitValue[1] // 78
                    if(prefix.isNotEmpty()){
                        one = prefix + one //adding - sign again to the number
                    }

                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                }
                else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/") // string method
                    // for ex 44 / 78
                    var one = splitValue[0] // 44
                    var two = splitValue[1] // 78
                    if(prefix.isNotEmpty()){
                        one = prefix + one//adding - sign again to the number
                    }

                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                }

            } catch ( e : java.lang.ArithmeticException)
                {
                    e.printStackTrace()
                }
        }
    }

}
