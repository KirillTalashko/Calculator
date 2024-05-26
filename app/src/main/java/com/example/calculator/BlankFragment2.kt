package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class BlankFragment2 : Fragment() {
    private lateinit var viewRoot: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewRoot = inflater.inflate(R.layout.fragment_blank2, container, false)
        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewRoot.findViewById<Button>(R.id.button_text2).setOnClickListener {
            findNavController().navigateUp()
        }
    }
}