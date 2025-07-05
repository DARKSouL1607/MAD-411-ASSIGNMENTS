package com.example.assignment6

import android.content.Intent

import android.os.Bundle

import android.util.Log // IMPORT FOR LOG
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    private val listOfExpense = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // THE LOG MESSAGE
        Log.d("LIFECYCLE", "THE METHOD ON CREATE CALLED")
        val expenseName = findViewById<EditText>(R.id.E1)
        val amount = findViewById<EditText>(R.id.E2)
        val date = findViewById<EditText>(R.id.E3)
        val addButton = findViewById<Button>(R.id.B1)
        val expenseList = findViewById<ListView>(R.id.L1)
        val removeButton = findViewById<Button>(R.id.R1)
        val showDetails = findViewById<Button>(R.id.B2)   // CREATED FOR SHOWING DETAILS
        val showTips = findViewById<Button>(R.id.B3)      // CREATED FOR SHOWING TIPS

        val expenseAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfExpense)
        expenseList.adapter = expenseAdapter
        addButton.setOnClickListener {
            val expName = expenseName.text.toString()
            val expAmount = amount.text.toString()
            val purchaseDate = date.text.toString()
            if (expName.isEmpty() || expAmount.isEmpty()) {
                Toast.makeText(this, "ENTER NAME AND AMOUNT", Toast.LENGTH_SHORT).show()
            } else {
                val expense = "$expName - $expAmount - $purchaseDate"
                listOfExpense.add(expense)
                expenseAdapter.notifyDataSetChanged()
                expenseName.text.clear()
                amount.text.clear()
                date.text.clear()
                Toast.makeText(this, "EXPENSE HAS BEEN ADDED", Toast.LENGTH_SHORT).show()
            }
        }
        // TO REMOVE LAST ADDED EXPENCE
        removeButton.setOnClickListener {
            if (listOfExpense.isNotEmpty()) {
                listOfExpense.removeAt(listOfExpense.size - 1)
                expenseAdapter.notifyDataSetChanged()
                Toast.makeText(this, "THE LAST EXPENSE HAS BEEN REMOVED!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "NOTHING TO REMOVE!", Toast.LENGTH_SHORT).show()
            }
        }
        // ASSIGNMENT 7
        // CREATED A BUTTON FOR FINANCIAL ADVICE
        // CREATED FOR SHOWING DETAILS
        showDetails.setOnClickListener {
            if (listOfExpense.isNotEmpty()) {
                val lastExpense = listOfExpense.last().split(" - ")
                // val intent = Intent(this, ExpenseDetailsActivity::class.java) // TO REDIRECT TOWARD THE SECOND SCREEN - SHOW DETAILS
                intent.putExtra("NAME", lastExpense[0])
                intent.putExtra("AMOUNT", lastExpense[1])
                intent.putExtra("DATE", lastExpense[2])
                // startActivity(intent)
            } else {
                Toast.makeText(this, "NO EXPENSE TO SHOW!", Toast.LENGTH_SHORT).show()
            }
        }
        showTips.setOnClickListener {
            // AUTO SUGGESTED LINE TO AVOID WARNING
            val financialTips =
                Intent(
                    Intent.ACTION_VIEW,
                    "https://www.edwardjones.ca/ca-en".toUri()
                ) // AUTO SUGGESTED LINE TO AVOID WARNING
            startActivity(financialTips)
        }
    }
    // CREATED LIFECYCLE METHOD - ON START
    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "THE METHOD ON START CALLED")
    }

    // CREATED LIFECYCLE METHOD - ON RESUME
    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "THE METHOD ON RESUME CALLED")
    }

    // CREATED LIFECYCLE METHOD - ON PAUSE
    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "THE METHOD ON PAUSE CALLED")
    }

    // CREATED LIFECYCLE METHOD - ON STOP
    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "THE METHOD ON STOP CALLED")
    }

    // CREATED LIFECYCLE METHOD - ON DESTROY
    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "THE METHOD ON DESTROY CALLED")
    }

}

