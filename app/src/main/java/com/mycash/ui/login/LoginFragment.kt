package com.mycash.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mycash.R
import com.mycash.databinding.FragmentLoginBinding
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.ValidationState
import com.mycash.domain.models.requests.LogInRequest
import com.mycash.ui.SharedViewModel
import com.mycash.utils.HelperMethods.gone
import com.mycash.utils.HelperMethods.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        loginViewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultApiCall.Loading -> {
                    binding.progressBar.visible()
                }

                is ResultApiCall.Success -> {
                    binding.progressBar.gone()
                    Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
                    sharedViewModel.setUserData(result.data.data)
                    val navController = findNavController()
                    navController.navigate(R.id.action_loginFragment_to_navigation_home)

                }

                is ResultApiCall.Failure -> {
                    binding.progressBar.gone()
                    Toast.makeText(
                        requireContext(),
                        "Login Failed: ${result.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResultApiCall.InputState -> {
                    binding.progressBar.gone()
                    when (result.errorState) {
                        ValidationState.EmptyEmail -> {
                            binding.emailTv.error = getString(R.string.please_enter_your_email)
                        }

                        ValidationState.IncorrectEmail -> {
                            binding.emailTv.error = getString(R.string.please_enter_a_valid_email)
                        }

                        ValidationState.EmptyPassword -> {
                            binding.passwordTv.error =
                                getString(R.string.please_enter_your_password)
                        }

                        ValidationState.EmptyConfirmPassword -> {

                        }

                        ValidationState.EmptyName -> {

                        }

                        ValidationState.EmptyPhone -> {

                        }

                        ValidationState.MustHasMore11LengthPhone -> {

                        }

                        ValidationState.MustHasMore14LengthName -> {
                        }

                        ValidationState.MustHasMore8LengthPassword -> {
                        }

                        ValidationState.PasswordNotMatch -> {
                        }

                        ValidationState.Valid -> {
                        }
                    }
                }
            }
        }
        binding.signupTv.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginBtn.setOnClickListener {
            loginViewModel.login(getLogInRequest())
        }
    }

    private fun getLogInRequest(): LogInRequest {
        val email = binding.emailEt.text.toString()
        val password = binding.passwordEt.text.toString()
        return LogInRequest(email, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}