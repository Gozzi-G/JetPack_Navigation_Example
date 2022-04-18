package com.prud.jetpack_navigation_example

import com.prud.jetpack_navigation_example.data.model.accounts.AccountsRepository
import com.prud.jetpack_navigation_example.data.model.accounts.AccountsRepositoryImpl
import com.prud.jetpack_navigation_example.data.model.boxes.BoxesRepository
import com.prud.jetpack_navigation_example.data.model.boxes.BoxesRepositoryImpl


object Repositories {

    val accountsRepository: AccountsRepository = AccountsRepositoryImpl()

    val boxesRepository: BoxesRepository = BoxesRepositoryImpl(accountsRepository)

}