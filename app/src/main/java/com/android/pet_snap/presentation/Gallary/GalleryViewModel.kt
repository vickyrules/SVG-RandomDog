package com.android.pet_snap.presentation.Gallary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pet_snap.data.local.asDomain
import com.android.pet_snap.data.repository.GalleryItemsRepository
import com.android.pet_snap.network.DogResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GalleryUiState(val dogList: List<DogResponse> = listOf())

@HiltViewModel
class GalleryViewModel @Inject constructor(private val galleryItemsRepository: GalleryItemsRepository) :
    ViewModel() {


    val galleryUiState: StateFlow<GalleryUiState> =
        galleryItemsRepository.getAllDogsStream().map { GalleryUiState(it.asDomain()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = GalleryUiState()
            )

    fun clearAllDogs() {
        viewModelScope.launch {
            galleryItemsRepository.deleteAllDog()
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


