package com.mycash.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mycash.R
import com.mycash.databinding.FragmentLoginBinding
import com.mycash.domain.model.ResultApiCall
import com.mycash.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultApiCall.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResultApiCall.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
                    sharedViewModel.setUserData(result.data.data)
                    val navController = findNavController()
                    navController.navigate(R.id.action_loginFragment_to_navigation_home)

                }
                is ResultApiCall.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Login Failed: ${result.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.signupTv.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            when {
                email.isEmpty() -> {
                    Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_SHORT).show()
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Toast.makeText(requireContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(requireContext(), "Please enter your password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    loginViewModel.login(email, password)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}