package com.prud.jetpack_navigation_example.screens.main.tabs.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.prud.jetpack_navigation_example.R
import com.prud.jetpack_navigation_example.databinding.FragmentEditProfileBinding

class EditProfileFragment: Fragment(R.layout.fragment_edit_profile) {
    private lateinit var binding: FragmentEditProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditProfileBinding.bind(view)
    }
}