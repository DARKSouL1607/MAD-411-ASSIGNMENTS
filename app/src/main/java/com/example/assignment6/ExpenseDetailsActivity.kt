package com.example.assignment6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExpenseDetailsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n") // TO CLEAR WARNINGS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val itemName = findViewById<TextView>(R.id.T1)
        val price = findViewById<TextView>(R.id.T2)
        val dateShow = findViewById<TextView>(R.id.T3)
        val exitButton = findViewById<Button>(R.id.exit)
        // TO LOAD DATA FROM THE INTENT I MADE IN MAIN ACTIVITY
        val item = intent.getStringExtra("NAME")
        val amount = intent.getStringExtra("AMOUNT")
        val date = intent.getStringExtra("DATE")
        // SHOWING DATA
        itemName.text = "ITEM NAME : $item"
        price.text = "PRICE : $amount"
        dateShow.text = "DATE : $date"
        // EXIT BUTTON TO MAIN SCREEN
        exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}