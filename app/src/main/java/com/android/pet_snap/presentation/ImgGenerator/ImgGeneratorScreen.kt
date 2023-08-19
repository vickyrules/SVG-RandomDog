package com.android.pet_snap.presentation.ImgGenerator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.pet_snap.R
import com.android.pet_snap.navigation.ImgGeneratorScreenDestiantion
import com.android.pet_snap.ui.theme.components.commons.AppBar
import com.android.pet_snap.ui.theme.components.commons.ErrorScreen
import com.android.pet_snap.ui.theme.components.commons.FirstTimeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImgGeneratorScreen(
    onNavigateUp: () -> Unit,
    generatorUiState: GeneratorUiState,
    getRandomImage: () -> Unit,
    viewModel: ImgGeneratorViewModel
) {
    val isloading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { AppBar(title = ImgGeneratorScreenDestiantion.title, navigateUp = onNavigateUp) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxHeight(0.4f),
                contentAlignment = Alignment.Center) {
                when (generatorUiState) {
                    is GeneratorUiState.Initial -> FirstTimeScreen(
                        modifier = Modifier.fillMaxSize(),
                        isFirstTime = generatorUiState.isFirstTime
                    )

                    is GeneratorUiState.Success -> Results(
                        randomImage = generatorUiState.image,
                    )

                    is GeneratorUiState.Error -> ErrorScreen(
                        modifier = Modifier.fillMaxSize(),
                        ErrorResponse = generatorUiState.errorResponse,
                    )
                }
            }

            Button(
                onClick = getRandomImage,
                enabled = !isloading,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Text(text = stringResource(R.string.generate))
            }
        }
    }
}


@Composable
fun Results(randomImage: String) {
    OutlinedCard(modifier = Modifier.wrapContentSize())
    {
        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data(randomImage)
                .crossfade(true)
                .build(),
            modifier = Modifier.size(IMAGE_SIZE.dp),
            placeholder = painterResource(R.drawable.loading_img),
            onError = { },
            contentDescription = "dog Image",
            contentScale = ContentScale.FillBounds
        )
    }
}

const val IMAGE_SIZE = 350