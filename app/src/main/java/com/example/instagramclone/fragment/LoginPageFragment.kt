package com.example.instagramclone.fragment

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentLoginPageBinding

class LoginPageFragment : Fragment() {
    private lateinit var binding: FragmentLoginPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerTextButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginPageFragment_to_registerFragment)
        }

        binding.loginButton.setOnClickListener {
            if (binding.loginPasswordTextField.text.toString().isEmpty()) {
                binding.loginPasswordTextField.setBackgroundResource(R.drawable.plaint_text_border_red)
                binding.loginPasswordEmptyError.visibility = View.VISIBLE
            }
            if (binding.loginUsernameTextField.text.toString().isEmpty()) {
                binding.loginUsernameTextField.setBackgroundResource(R.drawable.plaint_text_border_red)
                binding.loginUsernameEmptyError.visibility = View.VISIBLE
            }
        }

    }

}