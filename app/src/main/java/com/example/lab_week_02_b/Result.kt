package com.example.lab_week_02_b

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class Result : AppCompatActivity() {
    companion object {
        const val COLOR_KEY = "COLOR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)

        val colorCodeFromIntent = intent.getStringExtra(COLOR_KEY)

        Log.d("ResultActivity", "Received colorCodeFromIntent: $colorCodeFromIntent")

        if (colorCodeFromIntent != null &&
            colorCodeFromIntent.isNotBlank() &&
            !colorCodeFromIntent.equals("null", ignoreCase = true)) {
            try {
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCodeFromIntent"))
                resultMessage.text = getString(R.string.color_code_result_message, colorCodeFromIntent.uppercase())
            } catch (e: IllegalArgumentException) {
                Log.e("ResultActivity", "Invalid color format for '#$colorCodeFromIntent'", e)
                resultMessage.text = "Invalid Color Format: #$colorCodeFromIntent"
                backgroundScreen.setBackgroundColor(Color.GRAY)
            }
        } else {
            Log.w("ResultActivity", "colorCode is null, blank, or the string 'null'. Value: '$colorCodeFromIntent'")
            resultMessage.text = getString(R.string.color_code_not_provided)
            backgroundScreen.setBackgroundColor(Color.WHITE)
        }
    }
}
