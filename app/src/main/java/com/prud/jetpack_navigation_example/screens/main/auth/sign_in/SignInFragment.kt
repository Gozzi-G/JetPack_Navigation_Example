package com.prud.jetpack_navigation_example.screens.main.auth.sign_in

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.prud.jetpack_navigation_example.R
import com.prud.jetpack_navigation_example.Repositories
import com.prud.jetpack_navigation_example.databinding.FragmentSignInBinding
import com.prud.jetpack_navigation_example.utils.observeEvent
import com.prud.jetpack_navigation_example.utils.viewModelCreator

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    private val viewModel by viewModelCreator { SignInViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.signInButton.setOnClickListener { onSignInButtonPressed() }
        binding.signUpButton.setOnClickListener { onSignUpButtonPressed() }

        observeState()
        observeClearPasswordEvent()
        observeShowAuthErrorMessageEvent()
        observeNavigateToTabsEvent()
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner) {
        with(binding) {
            emailTextInput.error =
                if (it.emptyEmailError) getString(R.string.field_is_empty) else null
            passwordTextInput.error =
                if (it.emptyPasswordError) getString(R.string.field_is_empty) else null

            emailTextInput.isEnabled = it.enableViews
            passwordTextInput.isEnabled = it.enableViews
            signInButton.isEnabled = it.enableViews
            signUpButton.isEnabled = it.enableViews
            progressBar.visibility = if (it.showProgress) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun observeShowAuthErrorMessageEvent() =
        viewModel.showAuthToastEvent.observeEvent(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.invalid_email_or_password, Toast.LENGTH_SHORT)
                .show()
        }

    private fun observeClearPasswordEvent() =
        viewModel.clearPasswordEvent.observeEvent(viewLifecycleOwner) {
            binding.passwordEditText.text?.clear()
        }

    private fun observeNavigateToTabsEvent() =
        viewModel.navigateToTabsEvent.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(
                R.id.action_signInFragment_to_tabsFragment,
                null,
                navOptions {
                    popUpTo(R.id.signInFragment) {
                        inclusive = true
                    }
                })
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

        val direction = SignInFragmentDirections.actionSignInFragmentToSignUpFragment(emailArg)
        findNavController().navigate(direction)
    }
}