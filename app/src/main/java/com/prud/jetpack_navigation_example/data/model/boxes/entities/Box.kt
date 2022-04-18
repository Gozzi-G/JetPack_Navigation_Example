package com.prud.jetpack_navigation_example.data.model.boxes.entities

import androidx.annotation.StringRes

data class Box(
    val id: Int,
    @StringRes val colorNameRes: Int,
    val colorValue: Int
)
