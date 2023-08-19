package com.android.pet_snap.presentation.Gallary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.pet_snap.R
import com.android.pet_snap.navigation.GalleryScreenDestiantion
import com.android.pet_snap.network.DogResponse
import com.android.pet_snap.ui.theme.components.commons.AppBar
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    onNavigateUp: () -> Unit,
    galleryUiState: GalleryUiState,
    clearAllImages: () -> Unit
) {
    Scaffold(
        topBar = { AppBar(title = GalleryScreenDestiantion.title, navigateUp = onNavigateUp) }
    )
    { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(dogsList = galleryUiState.dogList)
            Button(
                onClick = clearAllImages,
                enabled = galleryUiState.dogList.size > 0,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Text(text = stringResource(R.string.clear_all))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPager(dogsList: List<DogResponse>) {
    val pagerState = rememberPagerState(0)

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
    ) {
        if (dogsList.size == 0) {
            Text(text = stringResource(R.string.no_images_to_show))
        } else {
            androidx.compose.foundation.pager.HorizontalPager(
                state = pagerState,
                pageCount = dogsList.size,

                ) { page ->
                OutlinedCard(modifier = Modifier.wrapContentSize())
                {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(context = LocalContext.current)
                            .data(dogsList[page].message)
                            .crossfade(true)
                            .build(),
                        modifier = Modifier.size(300.dp),
                        placeholder = painterResource(R.drawable.loading_img),
                        onError = { },
                        contentDescription = "dog Image",
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(dogsList.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)

                    )
                }
            }

        }
    }
}
