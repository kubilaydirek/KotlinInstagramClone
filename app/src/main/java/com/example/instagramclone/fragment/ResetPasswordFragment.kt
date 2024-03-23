package com.example.instagramclone.fragment

import RetrofitClient
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentRegisterBinding
import com.example.instagramclone.databinding.FragmentResetPasswordBinding
import com.example.instagramclone.model.VerificationCodeModel
import com.example.instagramclone.servis.InstagramService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.io.IOException
import java.lang.Exception

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resetPasswordBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginPageFragment)
        }

        binding.sendVerificationCodeButton.setOnClickListener {
            verificationCode();
        }
    }

    private fun verificationCode() {
        val retrofit = RetrofitClient.getInstance()
        val call = retrofit.create(InstagramService::class.java)
        val body = mapOf(
            "requestType" to "PASSWORD_RESET",
            "email" to binding.resetPasswordEpostaEditText.text.toString(),
        )
        try {
            call.sendCode(body).enqueue(object : Callback<VerificationCodeModel> {
                override fun onResponse(
                    call: Call<VerificationCodeModel>, response: Response<VerificationCodeModel>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(
                            context, "Doğrulama Kodu E-postanıza gönderildi.", Toast.LENGTH_LONG
                        ).show()
                        findNavController().navigate(R.id.action_resetPasswordFragment_to_loginPageFragment)
                    } else {
                        Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<VerificationCodeModel>, t: Throwable) {
                    Log.e("Error", "verificationCode Error ", t)

                }

            })

        } catch (ex: IOException) {
            Log.e("Error", "verificationCode error", ex)
            Toast.makeText(context, "Network error: ${ex.localizedMessage}", Toast.LENGTH_LONG)
                .show()

        }


    }
}

