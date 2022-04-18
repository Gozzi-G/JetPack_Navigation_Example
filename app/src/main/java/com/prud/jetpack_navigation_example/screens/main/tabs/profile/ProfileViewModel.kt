package com.prud.jetpack_navigation_example.screens.main.tabs.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prud.jetpack_navigation_example.data.model.accounts.AccountsRepository
import com.prud.jetpack_navigation_example.data.model.accounts.entities.Account
import com.prud.jetpack_navigation_example.utils.MutableLiveEvent
import com.prud.jetpack_navigation_example.utils.publishEvent
import com.prud.jetpack_navigation_example.utils.share
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account = _account.share()

    private val _restartFromLoginEvent = MutableLiveEvent<Unit>()
    val restartWithSignInEvent = _restartFromLoginEvent.share()

    init {
        viewModelScope.launch {
            accountsRepository.getAccount().collect {
                _account.value = it
            }
        }
    }

    fun logout() {
        // now logout is not async, so simply call it and restart the app from login screen
        accountsRepository.logout()
        restartAppFromLoginScreen()
    }

    private fun restartAppFromLoginScreen() {
        _restartFromLoginEvent.publishEvent()
    }
}