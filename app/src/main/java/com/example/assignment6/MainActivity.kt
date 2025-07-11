package com.example.assignment6

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import androidx.core.net.toUri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var itemNameInput: EditText
    private lateinit var itemPriceInput: EditText
    private lateinit var itemDateInput: EditText
    private lateinit var addExpenseButton: Button
    private lateinit var financialTipsButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExpenseAdapter
    private val expenseList = mutableListOf<Expense>()
    private lateinit var footerFragment: FooterFragment

    @SuppressLint("DefaultLocale") // TO CLEAR WARNING
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("LIFECYCLE", "THE ON CREATE METHOD CALLED") // LOG MESSAGE

        itemNameInput = findViewById(R.id.E1)
        itemPriceInput = findViewById(R.id.E2)
        itemDateInput =  findViewById(R.id.E3)
        addExpenseButton = findViewById(R.id.B1)
        financialTipsButton = findViewById(R.id.B2)
        recyclerView = findViewById(R.id.R1)
        // CREATED RECYCLERVIEW FOR ASSIGNMENT 7
        // IN ASSIGNMENT 6 I USED LISTVIEW IN WHICH I CAN NOT ABLE TO ADD BUTTONS AFTER EACH ACTIVITY HAS BEEN ENTERED
        // HENCE I CHANGED THE ENTIRE CODE I GAVE 2 DAYS BUT IT WAS NOT WORKING
        adapter = ExpenseAdapter(this, expenseList) { position ->
            expenseList.removeAt(position)
            adapter.notifyItemRemoved(position)
            updateTotalExpense()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        // THE HEADER AND FOOTER FRAGMENT
        loadFragment(HeaderFragment(), R.id.F1)
        footerFragment = FooterFragment()
        loadFragment(footerFragment, R.id.F2)
        // DATE DIALOG PICKER - COPIED FROM YOU GIVEN FILE
        itemDateInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    val formattedDate = String.format("%02d/%02d/%04d", day, month + 1, year)
                    itemDateInput.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        // THE ADD EXPENCE BUTTON AS I USED IN ASSIGNMENT 6
        addExpenseButton.setOnClickListener {
            val name = itemNameInput.text.toString().trim()
            val price = itemPriceInput.text.toString().trim()
            val date = itemDateInput.text.toString().trim()
            // I DID SEPARATE THING FOR ALL USER ENTER FIELD
            if (name.isEmpty()) {
                // I USED ERROR FUNCTION WHICH IS BETTER THAN USING TOAST
                // I ALREADY LEARNED THIS MEHTOD BACK IN
                itemNameInput.error = "ENTER THE ITEM NAME"
                return@setOnClickListener
            }
            if (price.isEmpty()) {
                itemPriceInput.error = "ENTER THE AMOUNT"
                return@setOnClickListener
            }
            if (date.isEmpty()) {
                Toast.makeText(this, "SELECT A DATE", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            expenseList.add(Expense(name, price, date))
            adapter.notifyItemInserted(expenseList.size - 1)
            updateTotalExpense()
            itemNameInput.text.clear()
            itemPriceInput.text.clear()
            itemDateInput.text.clear()
        }
        // CREATED A BUTTON FOR FINANCIAL TIP
        financialTipsButton.setOnClickListener {
            val url = "https://www.edwardjones.ca/ca-en".toUri() // WILL REDIRECT TO THE WEB BROWSER
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }
    }
    // THE TOTAL EXPENCE FOOTER FROM THE EXPENCE LIST
    private fun updateTotalExpense() {
        val total = expenseList.sumOf {
            try { it.amount.toDouble() } catch (_: Exception) { 0.0 }
        }
        footerFragment.updateTotal(total)
    }
    private fun loadFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(frameId, fragment)
            .commit()
    }
    private fun saveExpensesToPrefs() {
        val prefs = getSharedPreferences("expense_prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(expenseList)
        editor.putString("expenses", json)
        editor.apply()
    }

    private fun loadExpensesFromPrefs(): MutableList<Expense> {
        val prefs = getSharedPreferences("expense_prefs", MODE_PRIVATE)
        val json = prefs.getString("expenses", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Expense>>() {}.type
        return Gson().fromJson(json, type)
    }
    // ACTIVITY LIFECYCLES OVERRIDE AND LOG METHODS
    override fun onStart() { super.onStart(); Log.d("LIFECYCLE", "THE ON START CALLED") }
    override fun onResume() { super.onResume(); Log.d("LIFECYCLE", "THE ON RESUME CALLED") }
    override fun onPause() { super.onPause(); Log.d("LIFECYCLE", "THE ON PAUSE CALLED") }
    override fun onStop() { super.onStop(); Log.d("LIFECYCLE", "THE ON STOP CALLED") }
    override fun onDestroy() { super.onDestroy(); Log.d("LIFECYCLE", "THE ON DESTROY CALLED") }
}
