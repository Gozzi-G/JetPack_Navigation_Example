package com.prud.jetpack_navigation_example.screens.main.tabs.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prud.jetpack_navigation_example.data.model.boxes.BoxesRepository
import com.prud.jetpack_navigation_example.data.model.boxes.entities.Box
import com.prud.jetpack_navigation_example.utils.share
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val boxesRepository: BoxesRepository
): ViewModel() {

    private val _boxes = MutableLiveData<List<Box>>()
    val boxes = _boxes.share()

    init {
        viewModelScope.launch {
            boxesRepository.getBoxes(onlyActive = true).collect {
                _boxes.value = it
            }
        }
    }

}