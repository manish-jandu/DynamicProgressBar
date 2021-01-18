package com.example.dynamicprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

@Suppress("SENSELESS_COMPARISON")
class MainActivity : AppCompatActivity() {
    private var currentValue:Int = 0
    private var maximumValue:Int = 10
    private var weight:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar:ProgressBar = findViewById(R.id.progress_bar)
        val btnIncrease:Button = findViewById(R.id.btn_increase)
        val btnSubmit:Button = findViewById(R.id.btn_submit)
        val btnReset:Button = findViewById(R.id.btn_reset)
        val showText:TextView = findViewById(R.id.tv_progress)
        val etMaximum:EditText = findViewById(R.id.et_maximum)
        val etWeight:EditText = findViewById(R.id.et_weight)

        //btnIncrease
        // To increase progress with weight
        btnIncrease.setOnClickListener{
            if(weight<=(maximumValue - currentValue)){
                currentValue += weight
                setProgress(progressBar,showText)
            }
            else if(currentValue == maximumValue){
                Toast.makeText(
                        this,
                        "Progress is full,reset and start it again",
                        Toast.LENGTH_SHORT
                ).show()
            }
            else{
                Toast.makeText(
                        this,
                        "cannot increase progress",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
        //btnSubmit
        //To submit maximum and weight.
        btnSubmit.setOnClickListener {
            if(etWeight.text.isNotEmpty() && etMaximum.text.isNotEmpty()){
                var newWeight:Int = etWeight.text.toString().toInt()
                var newMaximumValue:Int = etMaximum.text.toString().toInt()
                submitProgress(newWeight,newMaximumValue,progressBar)
                setProgress(progressBar,showText)
            }
            else{
                Toast.makeText(
                        this,
                        "Fields cannot be empty",
                        Toast.LENGTH_SHORT).show()
            }
        }
        //btnReset
        // To reset Activity
        btnReset.setOnClickListener {
            weight =1
            maximumValue = 10
            currentValue = 0
            etMaximum.setText(maximumValue.toString())
            etWeight.setText(weight.toString())
            progressBar.setMax(maximumValue)
            setProgress(progressBar,showText)
        }
    }

    private fun setProgress(progressBar:ProgressBar,showText:TextView){
        if(currentValue<=maximumValue) {
            progressBar.progress = currentValue
            showText.text = "${currentValue}/${maximumValue}"
        }
        else{
            Toast.makeText(
                    this,
                    "current value is now equal to maximum value",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun submitProgress(newWeight:Int,
                               newMaximumValue:Int,
                               progressBar: ProgressBar){

        if(newMaximumValue>=currentValue){
            maximumValue = newMaximumValue
            progressBar.setMax(maximumValue)
            if((maximumValue-currentValue)>=newWeight ){
                weight = newWeight
            }else{
                Toast.makeText(
                        this,
                        "maximum weight is greater then current difference",
                        Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(
                    this,
                    "maximum value is greater then current value",
                    Toast.LENGTH_SHORT).show()
        }
    }
}