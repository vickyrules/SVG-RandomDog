package com.android.pet_snap.presentation.Gallary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun GalleryRoute(
    onNavigateUp:()->Unit,
    viewModel: GalleryViewModel
) {


 val galleryUiState by viewModel.galleryUiState.collectAsState()
    GalleryScreen(
        onNavigateUp =  onNavigateUp,
        galleryUiState = galleryUiState,
        clearAllImages = viewModel::clearAllDogs
    )
}

