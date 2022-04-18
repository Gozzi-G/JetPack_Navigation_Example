package com.prud.jetpack_navigation_example.data.model.accounts

import com.prud.jetpack_navigation_example.data.model.AccountAlreadyExistsException
import com.prud.jetpack_navigation_example.data.model.AuthException
import com.prud.jetpack_navigation_example.data.model.EmptyFieldException
import com.prud.jetpack_navigation_example.data.model.Field
import com.prud.jetpack_navigation_example.data.model.accounts.entities.Account
import com.prud.jetpack_navigation_example.data.model.accounts.entities.SignUpData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


/**
 * Simple implementation of [AccountsRepository] which holds accounts data in the app memory.
 */
class AccountsRepositoryImpl : AccountsRepository {

    private var currentAccountFlow = MutableStateFlow<Account?>(null)

    private val accounts = mutableListOf(
        AccountRecord(
            account = Account(
                userName = "admin",
                email = "admin@google.com"
            ),
            password = "123"
        )
    )

    init {
        currentAccountFlow.value = accounts[0].account
    }

    private class AccountRecord(
        var account: Account,
        val password: String
    )

    override suspend fun isSignedIn(): Boolean {
        delay(2000)
        return currentAccountFlow.value != null
    }

    override suspend fun signIn(email: String, password: String) {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (password.isBlank()) throw  EmptyFieldException(Field.Password)

        delay(1000)
        val record = getRecordByEmail(email)
        if (record != null && record.password == password) {
            currentAccountFlow.value = record.account
        } else {
            throw  AuthException()
        }
    }

    override suspend fun signUp(signUpData: SignUpData) {
        signUpData.validate()

        delay(1000)
        val accountRecord = getRecordByEmail(signUpData.email)
        if (accountRecord != null) throw AccountAlreadyExistsException()

        val newAccount = Account(
            userName = signUpData.username,
            email = signUpData.email,
            createAt = System.currentTimeMillis()
        )
        accounts.add(AccountRecord(newAccount, signUpData.password))
    }

    override fun logout() {

    }

    override fun getAccount(): Flow<Account?> {
        return currentAccountFlow
    }

    override suspend fun updateAccountUsername(newUsername: String) {

    }

    private fun getRecordByEmail(email: String) = accounts.firstOrNull {
        it.account.email == email
    }

}