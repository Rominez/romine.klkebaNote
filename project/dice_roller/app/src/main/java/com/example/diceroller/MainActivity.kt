package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var resultText : ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollbutton:Button = findViewById(R.id.roll_button);
        rollbutton.text = "let's roll"
        rollbutton.setOnClickListener {
            rollDice();
        }
        resultText = findViewById(R.id.dice_image)
    }

    private fun rollDice() {
        resultText.setImageResource(when(Random().nextInt(6)){
            0 -> R.drawable.dice_1
            1 -> R.drawable.dice_2
            2 -> R.drawable.dice_3
            3 -> R.drawable.dice_4
            4 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        })
    }
}
