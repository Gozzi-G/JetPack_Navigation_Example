package com.prud.jetpack_navigation_example.screens.main.auth.sign_in

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.prud.jetpack_navigation_example.R
import com.prud.jetpack_navigation_example.databinding.FragmentSignInBinding

class SingInFragment: Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
    }
}