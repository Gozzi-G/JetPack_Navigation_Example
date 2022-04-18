package com.prud.jetpack_navigation_example.screens.main.auth.sign_in

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.prud.jetpack_navigation_example.R
import com.prud.jetpack_navigation_example.Repositories
import com.prud.jetpack_navigation_example.databinding.FragmentSignInBinding
import com.prud.jetpack_navigation_example.utils.observeEvent
import com.prud.jetpack_navigation_example.utils.viewModelCreator

class SingInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    private val viewModel by viewModelCreator { SignInViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.signInButton.setOnClickListener { onSignInButtonPressed()}
        binding.signUpButton.setOnClickListener { onSignUpButtonPressed()}

        observeState()
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner) {
        with(binding) {
            emailTextInput.error = if (it.emptyEmailError) getString(R.string.field_is_empty) else null
            passwordTextInput.error = if (it.emptyPasswordError) getString(R.string.field_is_empty) else null

            emailTextInput.isEnabled = it.enableViews
            passwordTextInput.isEnabled = it.enableViews
            signInButton.isEnabled = it.enableViews
            signUpButton.isEnabled = it.enableViews
            progressBar.visibility = if (it.showProgress) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun observeShowAuthErrorMessageEvent() = viewModel.showAuthToastEvent.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), R.string.invalid_email_or_password, Toast.LENGTH_SHORT).show()
    }

    private fun observeClearPasswordEvent() = viewModel.clearPasswordEvent.observeEvent(viewLifecycleOwner) {
        binding.passwordEditText.text?.clear()
    }

    private fun observeNavigateToTabsEvent() = viewModel.navigateToTabsEvent.observeEvent(viewLifecycleOwner) {
        // user has signed in successfully
        TODO("Replace SignInFragment by TabsFragment here")
    }

    private fun onSignInButtonPressed() {
        viewModel.signIn(
            email = binding.emailEditText.text.toString(),
            password = binding.passwordEditText.text.toString()
        )
    }

    private fun onSignUpButtonPressed() {
        val email = binding.emailEditText.text.toString()
        val emailArg = email.ifBlank { null }

        // user want to create a new account
        TODO("Launch SignUpFragment here and send emailArg to it")
    }
}