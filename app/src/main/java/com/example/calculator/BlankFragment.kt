package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class BlankFragment : Fragment() {
    private lateinit var viewRoot: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewRoot = inflater.inflate(R.layout.fragment_blank, container, false)
        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewRoot.findViewById<Button>(R.id.button_text).setOnClickListener {
            findNavController().navigate(BlankFragmentDirections.actionBlankFragmentToBlankFragment2())
        }
    }

}