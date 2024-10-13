package com.example.tipcalculatorproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcalculatorproject.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the content view to the root of the binding
        setContentView(binding.root)

        // call the tip calc function using view binding
        binding.btnTip.setOnClickListener() {
            tipCalc()
        }


    }

    private fun tipCalc() {
        if (binding.billAmount.text.isNotEmpty()) {

            // get the billAmount entered in EditText, convert it to float

            val billAmount = binding.billAmount.text.toString().toFloat()

            // calculates the percentage

            val tenPer = billAmount * 0.1
            val fifPer = billAmount * 0.15
            val twenPer = billAmount * 0.2

            // display result tips in the Textview

            binding.tipresult.text = (

                    "The tips are as follows: \n" +
                            "10% = " + (tenPer + billAmount) + "\n" +
                            "15% = " + (fifPer + billAmount) + "\n" +
                            "20% = " + (twenPer + billAmount)
                    )
        } else {
            binding.tipresult.text = "YOU MUST ENTER A BILL AMOUNT"
        }
    }
}