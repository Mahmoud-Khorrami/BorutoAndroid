package com.mahapp1397.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mahapp1397.borutoapp.presentation.screens.details.DetailsScreen
import com.mahapp1397.borutoapp.presentation.screens.home.HomeScreen
import com.mahapp1397.borutoapp.presentation.screens.search.SearchScreen
import com.mahapp1397.borutoapp.presentation.screens.splash.SplashScreen
import com.mahapp1397.borutoapp.presentation.screens.welcome.WelcomeScreen
import com.mahapp1397.borutoapp.utils.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController)
{
    NavHost(navController = navController, startDestination = Screen.Splash.route)
    {
        composable(route = Screen.Splash.route)
        {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Welcome.route)
        {
            WelcomeScreen(navHostController = navController)
        }

        composable(route = Screen.Home.route)
        {
            HomeScreen(navController)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY){type = NavType.IntType}))
        {
            DetailsScreen(navHostController = navController)
        }

        composable(route = Screen.Search.route)
        {
            SearchScreen(navHostController = navController)
        }

    }
}