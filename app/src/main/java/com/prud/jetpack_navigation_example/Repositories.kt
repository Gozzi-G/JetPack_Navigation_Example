package com.prud.jetpack_navigation_example

import com.prud.jetpack_navigation_example.data.model.accounts.AccountsRepositoryImpl
import com.prud.jetpack_navigation_example.data.model.boxes.BoxesRepositoryImpl


object Repositories {

    val accountsRepository: AccountsRepositoryImpl = AccountsRepositoryImpl()

    val boxesRepository: BoxesRepositoryImpl = BoxesRepositoryImpl()

}