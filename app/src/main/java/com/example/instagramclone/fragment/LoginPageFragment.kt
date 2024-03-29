package com.example.instagramclone.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentLoginPageBinding
import com.example.instagramclone.model.LoginModel
import com.example.instagramclone.servis.InstagramService
import com.example.instagramclone.utils.Utils
import com.example.instagramclone.view.HomePageActivity
import com.example.instagramclone.view_model.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginPageFragment : Fragment() {
    private lateinit var binding: FragmentLoginPageBinding

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        binding.loginUsernameTextField.setText("kubilaydirekk@gmail.com");
        binding.loginPasswordTextField.setText("123321")
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.registerTextButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginPageFragment_to_registerFragment)
        }

        binding.resetPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginPageFragment_to_resetPasswordFragment)
        }

        binding.loginButton.setOnClickListener {
            if (binding.loginUsernameTextField.text.toString()
                    .isEmpty() || binding.loginPasswordTextField.text.toString().isEmpty()
            ) {
                if (binding.loginPasswordTextField.text.toString().isEmpty()) {
                    binding.loginPasswordTextField.setBackgroundResource(R.drawable.plaint_text_border_red)
                    binding.loginPasswordEmptyError.visibility = View.VISIBLE
                }
                if (binding.loginUsernameTextField.text.toString().isEmpty()) {
                    binding.loginUsernameTextField.setBackgroundResource(R.drawable.plaint_text_border_red)
                    binding.loginUsernameEmptyError.visibility = View.VISIBLE
                }
            } else {
                loginViewModel.login(binding.loginUsernameTextField.text.toString(),
                    binding.loginPasswordTextField.text.toString(),
                    {
                        val intent = Intent(context, HomePageActivity::class.java)
                        startActivity(intent)
                        activity?.finish();
                    },
                    {
                        Toast.makeText(
                            context,
                            "Kullanıcı adınızı veya şifrenizi kontrol ediniz.",
                            Toast.LENGTH_LONG
                        ).show()
                    })
            }


        }

        binding.loginUsernameTextField.addTextChangedListener {
            binding.loginUsernameTextField.setBackgroundResource(R.drawable.plaint_text_border)
            binding.loginUsernameEmptyError.visibility = View.GONE
        }
        binding.loginPasswordTextField.addTextChangedListener {
            binding.loginPasswordTextField.setBackgroundResource(R.drawable.plaint_text_border)
            binding.loginPasswordEmptyError.visibility = View.GONE
        }

    }


}