package com.prud.jetpack_navigation_example.screens.main.auth.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prud.jetpack_navigation_example.data.model.AuthException
import com.prud.jetpack_navigation_example.data.model.EmptyFieldException
import com.prud.jetpack_navigation_example.data.model.accounts.AccountsRepository
import com.prud.jetpack_navigation_example.utils.MutableUnitLiveEvent
import com.prud.jetpack_navigation_example.utils.publishEvent
import com.prud.jetpack_navigation_example.utils.requireValue
import com.prud.jetpack_navigation_example.utils.share
import kotlinx.coroutines.launch

class SignInViewModel(
    private val accountsRepository: AccountsRepository
): ViewModel() {
    private val _state = MutableLiveData(State())
    val state = _state.share()

    private val _clearPasswordEvent = MutableUnitLiveEvent()
    val clearPasswordEvent = _clearPasswordEvent.share()

    private val _showAuthErrorToastEvent = MutableUnitLiveEvent()
    val showAuthToastEvent = _showAuthErrorToastEvent.share()

    private val _navigateToTabsEvent = MutableUnitLiveEvent()
    val navigateToTabsEvent = _navigateToTabsEvent.share()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        showProgress()
        try {
            accountsRepository.signIn(email, password)
            launchTabScreen()
        } catch (e : EmptyFieldException) {
            processEmptyFieldException(e)
        } catch (e: AuthException) {
            processAuthException()
        }
    }


    private fun showProgress() {
        _state.value = State(signInInProgress = true)
    }

    private fun clearPasswordEvent() = _clearPasswordEvent.publishEvent()

    private fun showAuthErrorToast() = _showAuthErrorToastEvent.publishEvent()

    private fun launchTabScreen() = _navigateToTabsEvent.publishEvent()

    private fun processEmptyFieldException(e: EmptyFieldException) {
        _state.value = _state.requireValue()
    }

    private fun processAuthException() {
        _state.value = _state.requireValue().copy(
            signInInProgress = false
        )
        clearPasswordEvent()
        showAuthErrorToast()
    }

    data class State(
        val emptyEmailError: Boolean = false,
        val emptyPasswordError: Boolean = false,
        val signInInProgress: Boolean = false
    ) {
        val showProgress: Boolean get() = signInInProgress
        val enableViews: Boolean get() = !signInInProgress
    }
}