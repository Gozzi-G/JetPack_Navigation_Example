package com.prud.jetpack_navigation_example.screens.main.tabs.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.prud.jetpack_navigation_example.R
import com.prud.jetpack_navigation_example.databinding.FragmentBoxBinding

class BoxFragment: Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)
    }
}