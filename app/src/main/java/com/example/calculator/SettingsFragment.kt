package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.calculator.databinding.FragmentSettingsBinding
import com.example.calculator.extentions.log

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(
            requireContext(),
            "${Settings.loadTheme(requireContext())}",
            Toast.LENGTH_LONG
        ).show()
        when (Settings.loadTheme(requireContext())) {
            2 -> {
                binding.radioButtonLightStyle.isChecked = false
                binding.radioButtonNightStyle.isChecked = true
            }

            else -> {
                binding.radioButtonLightStyle.isChecked = true
                binding.radioButtonNightStyle.isChecked = false
            }
        }

        binding.radioButtonLightStyle.setOnClickListener {
            Settings.saveTheme(requireContext(), 1)
            "${Settings.loadTheme(requireContext())}".log()
            requireActivity().recreate()

        }
        binding.radioButtonNightStyle.setOnClickListener {
            Settings.saveTheme(requireContext(), 2)
            "${Settings.loadTheme(requireContext())}".log()
            requireActivity().recreate()
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.buttonClick.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToBlankFragment())
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}