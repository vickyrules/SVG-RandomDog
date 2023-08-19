package com.android.pet_snap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.android.pet_snap.navigation.NavGraph
import com.android.pet_snap.presentation.Gallary.GalleryViewModel
import com.android.pet_snap.presentation.ImgGenerator.ImgGeneratorViewModel
import com.android.pet_snap.presentation.home.HomeScreen
import com.android.pet_snap.ui.theme.PetSnapTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            PetSnapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val imgGeneratorViewModel by viewModels<ImgGeneratorViewModel>()
                    val galleryViewModel by viewModels<GalleryViewModel>()
                    NavGraph(imgGeneratorViewModel = imgGeneratorViewModel, galleryViewModel = galleryViewModel )
                }
            }
        }
    }
}

