package com.android.pet_snap.presentation.ImgGenerator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pet_snap.data.local.asEntity
import com.android.pet_snap.data.repository.DogImageRepository
import com.android.pet_snap.data.repository.GalleryItemsRepository
import com.android.pet_snap.network.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface GeneratorUiState {
    data class Success(val image: String) : GeneratorUiState
    data class Error(val errorResponse: ErrorResponse) : GeneratorUiState
    data class Initial(val isFirstTime: Boolean) : GeneratorUiState

}


@HiltViewModel
class ImgGeneratorViewModel @Inject constructor(
    private val dogImageRepository: DogImageRepository,
    private val galleryItemsRepository: GalleryItemsRepository
) :
    ViewModel() {

    var homeUiState: GeneratorUiState by mutableStateOf(GeneratorUiState.Initial(false))
        private set

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    fun reset() {
        homeUiState = GeneratorUiState.Initial(true)
        _isLoading.update { false }
    }

    fun getRandomImage() {
        viewModelScope.launch {
            GeneratorUiState.Initial(false)
            _isLoading.update { true }
            homeUiState = try {
                val response = dogImageRepository.getRandomImage()
                galleryItemsRepository.insertDog(response.asEntity())
                _isLoading.update { false }
                GeneratorUiState.Success(response.message)
            } catch (e: IOException) {
                GeneratorUiState.Error(
                    ErrorResponse(
                        message = e.message.toString(),
                        statusCode = e.hashCode()
                    )
                )
            } catch (e: HttpException) {
                GeneratorUiState.Error(
                    ErrorResponse(
                        message = e.message.toString(),
                        statusCode = e.code()
                    )
                )
            }
        }
    }
}