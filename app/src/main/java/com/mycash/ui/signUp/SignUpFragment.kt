package com.mycash.ui.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mycash.R
import com.mycash.databinding.FragmentSignUpBinding
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.ValidationState
import com.mycash.domain.models.requests.SingUpRequest
import com.mycash.utils.HelperMethods.gone
import com.mycash.utils.HelperMethods.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        signUpViewModel.signupResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultApiCall.Loading -> {
                    binding.progressBar.visible()
                }

                is ResultApiCall.Success -> {
                    binding.progressBar.gone()
                    Toast.makeText(
                        requireContext(),
                        "User created Successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    val navController = findNavController()
                    navController.navigate(R.id.action_signUpFragment_to_navigation_home)
                }

                is ResultApiCall.Failure -> {
                    binding.progressBar.gone()
                    Toast.makeText(
                        requireContext(),
                        "Signup Failed: ${result.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResultApiCall.InputState -> {
                    binding.progressBar.gone()
                    when (result.errorState) {
                        ValidationState.EmptyName -> binding.nameTv.error =
                            getString(R.string.please_enter_your_name)

                        ValidationState.MustHasMore14LengthName -> binding.nameTv.error =
                            getString(R.string.name_must_be_at_least_14_characters)

                        ValidationState.EmptyEmail -> {
                            binding.nameTv.error =
                                getString(R.string.enter_your_email)

                        }

                        ValidationState.IncorrectEmail -> {
                            binding.nameTv.error =
                                getString(R.string.please_enter_a_valid_email)
                        }


                        ValidationState.EmptyPassword -> {
                            binding.passwordTv.error =
                                getString(R.string.please_enter_your_password)
                        }

                        ValidationState.MustHasMore8LengthPassword -> {
                            binding.passwordTv.error =
                                getString(R.string.password_must_be_at_least_8_characters)
                        }

                        ValidationState.EmptyConfirmPassword -> {
                            binding.confirmPasswordTv.error =
                                getString(R.string.please_confirm_your_password)
                        }

                        ValidationState.PasswordNotMatch -> {
                            binding.confirmPasswordTv.error =
                                getString(R.string.passwords_do_not_match)
                        }

                        ValidationState.EmptyPhone -> {
                            binding.phoneTv.error =
                                getString(R.string.please_enter_your_phone_number)
                        }

                        ValidationState.MustHasMore11LengthPhone -> {
                            binding.phoneTv.error =
                                getString(R.string.phone_number_must_be_at_least_11_characters)
                        }

                        ValidationState.Valid -> {
                        }
                    }
                }
            }
        }
        binding.loginTv.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.signupBtn.setOnClickListener {
            signUpViewModel.signUp(getSignUpRequest())
        }
    }

    private fun getSignUpRequest(): SingUpRequest {
        val email = binding.emailEt.text.toString()
        val password = binding.passwordEt.text.toString()
        val confirmPassword = binding.confirmPasswordEt.text.toString()
        val name = binding.nameEt.text.toString()
        val phone = binding.phoneEt.text.toString()
        return SingUpRequest(name, email, password, phone, confirmPassword)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}