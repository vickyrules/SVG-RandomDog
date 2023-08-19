package com.android.pet_snap.presentation.ImgGenerator

import androidx.compose.runtime.Composable

@Composable
fun ImgGeneratorRoute(
    onNavigateUp:()->Unit,
    viewModel: ImgGeneratorViewModel) {

    ImgGeneratorScreen(
       onNavigateUp =  onNavigateUp,
        generatorUiState = viewModel.homeUiState,
        getRandomImage = viewModel::getRandomImage,
        viewModel = viewModel
    )
}