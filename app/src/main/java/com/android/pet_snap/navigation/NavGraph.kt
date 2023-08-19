package com.android.pet_snap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.pet_snap.presentation.Gallary.GalleryRoute
import com.android.pet_snap.presentation.Gallary.GalleryViewModel
import com.android.pet_snap.presentation.ImgGenerator.ImgGeneratorRoute
import com.android.pet_snap.presentation.ImgGenerator.ImgGeneratorViewModel
import com.android.pet_snap.presentation.home.HomeScreen


@Composable
fun NavGraph(
    imgGeneratorViewModel: ImgGeneratorViewModel,
    galleryViewModel: GalleryViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        startDestination = HomeScreenDestiantion.route,
        navController = navController
    ) {
        composable(
            route = HomeScreenDestiantion.route
        ) {
            HomeScreen(
                nav_to_ImgGenerator = { navController.navigate(ImgGeneratorScreenDestiantion.route) },
                nav_to_Gallery = { navController.navigate(GalleryScreenDestiantion.route) }
            )
        }
        composable(
            route = ImgGeneratorScreenDestiantion.route
        ) {
            imgGeneratorViewModel.reset()//remove if want to set the recently fetched image on the screen when user enter again
            ImgGeneratorRoute(
                viewModel = imgGeneratorViewModel,
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = GalleryScreenDestiantion.route
        ) {
            GalleryRoute(
                viewModel = galleryViewModel,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}