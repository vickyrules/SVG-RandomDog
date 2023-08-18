package com.android.pet_snap.navigation

interface NavigationDestinations{
    val title:String
    val route:String
}

object HomeScreenDestiantion : NavigationDestinations{
    override val title: String = "Home"
    override val route: String = "HomeScreen"
}
object ImgGeneratorScreenDestiantion : NavigationDestinations{
    override val title: String = "Generate Dogs!"
    override val route: String = "ImgGeneratorScreen"
}
object GalleryScreenDestiantion : NavigationDestinations{
    override val title: String = "My recently Generated Dogs!"
    override val route: String = "GalleryScreen"
}