package com.example.assignment6

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private val context: Context,
    private val expenses: MutableList<Expense>,
    private val onRemove: (Int) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.T1)
        val showDetailsBtn: Button = view.findViewById(R.id.B1)
        val removeBtn: Button = view.findViewById(R.id.B2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_recycleview, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]

        holder.itemName.text ="ITEM : ${expense.name}"

        holder.showDetailsBtn.setOnClickListener {
            val intent = Intent(context, ExpenseDetailsActivity::class.java).apply {
                putExtra("NAME", expense.name)
                putExtra("AMOUNT", expense.amount)
                putExtra("DATE", expense.date)
            }
            context.startActivity(intent)
        }
        holder.removeBtn.setOnClickListener {
            onRemove(holder.adapterPosition)
        }

    }
    override fun getItemCount(): Int = expenses.size
}