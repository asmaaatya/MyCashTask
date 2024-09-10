package com.mycash.ui.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mycash.R
import com.mycash.databinding.FragmentSignUpBinding
import com.mycash.domain.model.ResultApiCall
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        signUpViewModel.signupResult.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultApiCall.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultApiCall.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "User created Successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    val navController = findNavController()
                    navController.navigate(R.id.action_loginFragment_to_navigation_home)
                }

                is ResultApiCall.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Signup Failed: ${result.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        binding.loginTv.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.signupBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmPassword = binding.confirmPasswordEt.text.toString()
            val name = binding.nameEt.text.toString()
            val phone = binding.phoneEt.text.toString()
            when {
                name.isEmpty() -> {
                    Toast.makeText(requireContext(), "Please enter your name", Toast.LENGTH_SHORT).show()
                }
                name.length < 14 -> {
                    Toast.makeText(requireContext(), "Name must be at least 14 characters", Toast.LENGTH_SHORT).show()
                }
                email.isEmpty() -> {
                    Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_SHORT).show()
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Toast.makeText(requireContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(requireContext(), "Please enter your password", Toast.LENGTH_SHORT).show()
                }
                password.length < 8 -> {
                    Toast.makeText(requireContext(), "Password must be at least 8 characters", Toast.LENGTH_SHORT).show()
                }
                confirmPassword.isEmpty() -> {
                    Toast.makeText(requireContext(), "Please confirm your password", Toast.LENGTH_SHORT).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                phone.isEmpty() -> {
                    Toast.makeText(requireContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show()
                }
                phone.length < 11 -> {
                    Toast.makeText(requireContext(), "Phone number must be at least 11 characters", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    signUpViewModel.signUp(name, email, password, phone)
                }

            }
        }
            }

            override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
            }
        }