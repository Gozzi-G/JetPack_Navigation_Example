package com.prud.jetpack_navigation_example.data.model.accounts.entities

/**
 * Information about the user.
 */
data class Account(
    val userName: String,
    val email: String,
    val createAt: Long = UNKNOWN_CREATED_AT
) {
    companion object {
        const val UNKNOWN_CREATED_AT = 0L
    }
}