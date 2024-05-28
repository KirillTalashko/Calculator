package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.calculator.databinding.FragmentSettingsBinding

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
                requireContext().setTheme(R.style.AppThemeDark)
                binding.radioButtonLightStyle.isChecked = false
                binding.radioButtonNightStyle.isChecked = true
            }

            else -> {
                requireContext().setTheme(R.style.AppThemeLight)
                binding.radioButtonLightStyle.isChecked = true
                binding.radioButtonNightStyle.isChecked = false
            }
        }
        binding.radio.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonNightStyle -> Settings.saveTheme(requireContext(), 2)
                R.id.radioButtonLightStyle -> Settings.saveTheme(requireContext(), 1)
                else -> Settings.saveTheme(requireContext(), 1)
            }
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