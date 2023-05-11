package com.mahapp1397.borutoapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mahapp1397.borutoapp.R
import com.mahapp1397.borutoapp.navigation.Screen
import com.mahapp1397.borutoapp.ui.theme.Purple500
import com.mahapp1397.borutoapp.ui.theme.Purple700

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel())
{
    val rotate = remember { Animatable(0f) }

    val onBoardingComplete by splashViewModel.onBoardingComplete.collectAsState()

    LaunchedEffect(key1 = true){
        rotate.animateTo(targetValue = 360f,
                         animationSpec = tween(
                             durationMillis = 1000,
                             delayMillis = 200))

        navController.popBackStack()

        if (onBoardingComplete)
            navController.navigate(Screen.Home.route)
        else
            navController.navigate(Screen.Welcome.route)

    }
    Splash(rotate.value)
}

@Composable
fun Splash(degree: Float)
{
    if (isSystemInDarkTheme())
    {
        Box(modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Image(
                modifier = Modifier.rotate(degrees = degree),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "")
        }
    }

    else
    {
        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
            .fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Image(
                modifier = Modifier.rotate(degrees = degree),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "")
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview()
{
    Splash(0f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview()
{
    Splash(0f)
}