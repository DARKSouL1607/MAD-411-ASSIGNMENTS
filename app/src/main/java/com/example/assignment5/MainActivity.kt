package com.example.assignment5

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var n1EditText : EditText
    private lateinit var t1TextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        n1EditText = findViewById(R.id.E1)
        t1TextView = findViewById(R.id.T1)
        val showButton = findViewById<Button>(R.id.B1)
        showButton.setOnClickListener {
            showName()
        }
    }
    private fun showName() {
        val showName = n1EditText.text.toString().trim()
        t1TextView.text = "HEY , $showName !"
    }
}