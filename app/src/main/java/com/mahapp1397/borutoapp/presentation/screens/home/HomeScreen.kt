package com.mahapp1397.borutoapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mahapp1397.borutoapp.navigation.Screen
import com.mahapp1397.borutoapp.presentation.comon.ListContent
import com.mahapp1397.borutoapp.ui.theme.statusBar

@ExperimentalCoilApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
              )
{
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.statusBar)

    Scaffold(
        topBar = { HomeTopBar(onSearchClick = {navHostController.navigate(Screen.Search.route)})},

        content = {ListContent(heroes = allHeroes,
                               navHostController = navHostController)})
}