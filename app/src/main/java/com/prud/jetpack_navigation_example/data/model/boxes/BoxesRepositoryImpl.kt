package com.prud.jetpack_navigation_example.data.model.boxes

import com.prud.jetpack_navigation_example.data.model.accounts.AccountsRepository
import com.prud.jetpack_navigation_example.data.model.boxes.entities.Box
import kotlinx.coroutines.flow.Flow


class BoxesRepositoryImpl(
    private val accountsRepository: AccountsRepository
) : BoxesRepository {

    override fun getBoxes(onlyActive: Boolean): Flow<List<Box>> {
        TODO("Not yet implemented")
    }

    override suspend fun activateBox(box: Box) {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateBox(box: Box) {
        TODO("Not yet implemented")
    }
}