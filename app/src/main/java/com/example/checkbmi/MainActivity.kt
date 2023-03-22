package com.example.checkbmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val win = findViewById<EditText>(R.id.win)
        val hin = findViewById<EditText>(R.id.hin)
        val calcButton = findViewById<Button>(R.id.calcbtn)

        calcButton.setOnClickListener {
            val weight = win.text.toString()
            val height = hin.text.toString()
            if(validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2dig = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2dig)
            }

        }

    }
    private fun validateInput(weight:String?,height:String?):Boolean{

        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText( this,"weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText( this,"height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }
    private fun displayResult(bmi:Float){
        val resultindex = findViewById<TextView>(R.id.result1)
        val resultdescp = findViewById<TextView>(R.id.result2)

        resultindex.text = bmi.toString()

        var resulttext = ""
        var color = 0

        when{
            bmi<18.50 ->{
                resulttext = "Underweight"
                color = R.color.under_weight
        }
            bmi in 18.50..24.99->{
                resulttext = "Healthy"
                color = R.color.normal
        }
            bmi in 25.00..29.99->{
                resulttext = "Overweight"
                color = R.color.over_weight
        }
            bmi>29.99->{
                resulttext = "Obese"
                color = R.color.obese
        }

        }
        resultdescp.setTextColor(ContextCompat.getColor( this,color))
        resultdescp.text =resulttext

    }
}