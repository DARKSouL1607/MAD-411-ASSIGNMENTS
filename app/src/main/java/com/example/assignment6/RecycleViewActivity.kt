package com.example.assignment6

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecycleViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExpenseAdapter
    private val expenseList = mutableListOf<Expense>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleview)
        recyclerView = findViewById(R.id.R1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpenseAdapter(this, expenseList) { position ->
            expenseList.removeAt(position)
            adapter.notifyItemRemoved(position)
            Toast.makeText(this, "Expense removed", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
    }
}
