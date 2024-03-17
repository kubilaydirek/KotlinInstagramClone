package com.example.instagramclone.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginPageFragment)
        }
        binding.registerButton.setOnClickListener {
            if (binding.registerUsernameTextField.text.toString().isEmpty()) {
                binding.registerUsernameTextField.error = "Kullanıcı adı doldurulması zorunludur."
            }
            if (binding.registerEpostaTextField.text.toString().isEmpty()) {
                binding.registerEpostaTextField.error = "E-posta doldurulması zorunludur."
            }
            if (binding.registerPasswordTextField.text.toString().isEmpty()) {
                binding.registerPasswordTextField.error = "Şifre alanı doldurulması zorunludur."
            }
        }
    }
}