package com.mahapp1397.borutoapp.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mahapp1397.borutoapp.utils.Constants.BASE_URL
import com.mahapp1397.borutoapp.utils.PaletteGenerator.ConvertImageUrlToBitmap
import com.mahapp1397.borutoapp.utils.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel())
{
    val selectedHero by detailsScreenViewModel.selectedHero.collectAsState()
    val colorPalette by detailsScreenViewModel.colorPalette

    if (colorPalette.isNotEmpty())
        DetailsContent(navHostController = navHostController,
                       selectedHero = selectedHero,
                      colors = colorPalette)
    else
        detailsScreenViewModel.generateColorPalette()

    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        detailsScreenViewModel.uiEvent.collectLatest { event ->

            when(event)
            {
                is UiEvent.generateColorPalett -> {

                    val bitmap = ConvertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        context = context)

                    if (bitmap != null)
                        detailsScreenViewModel.setColorPalette(colors = extractColorsFromBitmap(bitmap))
                }
            }

        }
    }
}