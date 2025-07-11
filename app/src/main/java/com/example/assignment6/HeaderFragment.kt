package com.example.assignment6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class HeaderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_header, container, false)
        // CREATED BUTTON TO SHOW A ToAST
        val titleButton = view.findViewById<Button>(R.id.H1)
        titleButton.setOnClickListener {
            Toast.makeText(requireContext(), "THIS APPLICATION IS CREATED BY VRAJ PANCHAL", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}