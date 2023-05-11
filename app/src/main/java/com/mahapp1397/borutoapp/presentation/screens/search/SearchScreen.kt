package com.mahapp1397.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mahapp1397.borutoapp.presentation.comon.ListContent
import com.mahapp1397.borutoapp.ui.theme.statusBar

@ExperimentalCoilApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel())
{

    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.statusBar)

    Scaffold(
        topBar = { SearchTopBar(text = searchQuery,
                                onTextChange = {searchViewModel.updateSearchQuery(it)},
                                onSearchClicked = {searchViewModel.searchHeroes(it)},
                                onCloseClicked = { navHostController.popBackStack()})},

        content = { ListContent(heroes = heroes, navHostController = navHostController)})
}