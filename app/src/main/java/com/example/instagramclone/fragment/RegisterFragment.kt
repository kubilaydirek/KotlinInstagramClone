package com.example.instagramclone.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentRegisterBinding
import com.example.instagramclone.model.RegisterModel
import com.example.instagramclone.servis.InstagramService
import com.example.instagramclone.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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
            if (binding.registerUsernameTextField.text.toString()
                    .isNotEmpty() && binding.registerEpostaTextField.text.toString()
                    .isNotEmpty() && binding.registerPasswordTextField.text.toString().isNotEmpty()
            ) {
                register();
            }
        }
    }

    private fun register() {
        val retrofit = Retrofit.Builder().baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(InstagramService::class.java)
        val body = mapOf(
            "email" to binding.registerEpostaTextField.text.toString(),
            "password" to binding.registerPasswordTextField.text.toString(),
            "displayName" to binding.registerUsernameTextField.text.toString(),
            "returnSecureToken" to true
        )
        service.register(body).enqueue(object : Callback<RegisterModel> {
            override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.registerUsernameTextField.text.clear();
                        binding.registerPasswordTextField.text.clear();
                        binding.registerEpostaTextField.text.toString();
                        findNavController().navigate(R.id.action_registerFragment_to_loginPageFragment)
                        Toast.makeText(
                            context,
                            "Başarılı bir şekilde kayıt olundu.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(
                        context,
                        "Bu e-posta ile daha önce kayıt olundu.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                println(t)
            }

        })
    }
}