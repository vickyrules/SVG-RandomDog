package com.android.pet_snap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.pet_snap.presentation.home.HomeScreen


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        startDestination = HomeScreenDestiantion.route,
        navController = navController
    ){
        composable(
            route = HomeScreenDestiantion.route){
            HomeScreen(
                nav_to_ImgGenerator = {navController.navigate(ImgGeneratorScreenDestiantion.route)},
                nav_to_Gallery = {navController.navigate(GalleryScreenDestiantion.route)}
            )
        }
        composable(
            route = ImgGeneratorScreenDestiantion.route){

        }
        composable(
            route = GalleryScreenDestiantion.route){
        }
    }
}