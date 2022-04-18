package com.prud.jetpack_navigation_example.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prud.jetpack_navigation_example.data.model.accounts.AccountsRepository
import com.prud.jetpack_navigation_example.utils.MutableLiveEvent
import com.prud.jetpack_navigation_example.utils.publishEvent
import com.prud.jetpack_navigation_example.utils.share
import kotlinx.coroutines.launch

/**
 * SplashViewModel checks whether user is signed-in or not.
 */
class SplashViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _launchMainScreenEvent = MutableLiveEvent<Boolean>()
    val launchMainScreenEvent = _launchMainScreenEvent.share()

    init {
        viewModelScope.launch {
            _launchMainScreenEvent.publishEvent(accountsRepository.isSignedIn())
        }
    }
}