package com.prud.jetpack_navigation_example.screens.main.auth.sign_up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.prud.jetpack_navigation_example.R
import com.prud.jetpack_navigation_example.databinding.FragmentSignUpBinding

class SignUpFragment: Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
    }
}