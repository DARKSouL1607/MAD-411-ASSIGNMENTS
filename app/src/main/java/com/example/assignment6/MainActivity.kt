package com.example.assignment6
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // GETTING ALL ELEMENTS ON SCREEN
        val expenseName = findViewById<EditText>(R.id.E1)
        val amount = findViewById<EditText>(R.id.E2)
        val date = findViewById<EditText>(R.id.E3)
        val expenceButton = findViewById<Button>(R.id.B1)
        val expenceList = findViewById<ListView>(R.id.L1)
        val removeBtn = findViewById<Button>(R.id.R1)

        // CREATED A LIST AND ADAPTER
        val listOfExpence = mutableListOf<String>()
        val expenceAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfExpence)
        expenceList.adapter = expenceAdapter

        // BUTTON - ON CLICK EVENT
        expenceButton.setOnClickListener {
            val expName = expenseName.text.toString()
            val expAmount = amount.text.toString()
            val purchaseDate = date.text.toString()
            if (expName.isEmpty() || expAmount.isEmpty()) {
                Toast.makeText(this, "ENTER NAME AND AMOUNT ", Toast.LENGTH_SHORT).show()
            } else {
                val expence = "$expName - $expAmount - $purchaseDate"
                // WILL ADD DETAILS IN THE BOX
                listOfExpence.add(expence)
                // I DID NOT FIND THIS IN EXAMPLES BUT AS I ALREADY USED IN MY LAST SEM HENCE I DID THIS
                expenceAdapter.notifyDataSetChanged()

                // CLEAR THE FIELDS
                expenseName.text.clear()
                amount.text.clear()
                date.text.clear()
                Toast.makeText(this, "EXPENCE HAS BEEN ADDED ", Toast.LENGTH_SHORT).show()
            }
        }
        // TEXT CHANGE
        expenseName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        // REMOVE BUTTON
        removeBtn.setOnClickListener {
            if (listOfExpence.isNotEmpty()) {
                listOfExpence.removeAt(listOfExpence.size - 1)
                expenceAdapter.notifyDataSetChanged() // UPDATE THE LIST VIEW
                Toast.makeText(this, "THE LAST EXPENCE HAS BEEN REMOVED!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "NOTHING REMOVED!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}