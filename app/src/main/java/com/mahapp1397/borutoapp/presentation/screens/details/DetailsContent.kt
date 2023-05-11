@file:OptIn(ExperimentalCoilApi::class)

package com.mahapp1397.borutoapp.presentation.screens.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mahapp1397.borutoapp.R
import com.mahapp1397.borutoapp.domain.models.Hero
import com.mahapp1397.borutoapp.presentation.component.InfoBox
import com.mahapp1397.borutoapp.presentation.component.OrderedList
import com.mahapp1397.borutoapp.ui.theme.CLOSE_ICON_SIZE
import com.mahapp1397.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.mahapp1397.borutoapp.ui.theme.LARGE_PADDING
import com.mahapp1397.borutoapp.ui.theme.MEDIUM_PADDING
import com.mahapp1397.borutoapp.ui.theme.MIN_SHEET_HEIGHT
import com.mahapp1397.borutoapp.ui.theme.SMALL_PADDING
import com.mahapp1397.borutoapp.ui.theme.titleColor
import com.mahapp1397.borutoapp.utils.Constants.ABOUT_TEXT_MAX_LINE
import com.mahapp1397.borutoapp.utils.Constants.BASE_URL


@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    selectedHero: Hero?,
    colors: Map<String, String>)
{

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#FFFFFF") }

    LaunchedEffect(key1 = selectedHero)
    {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(parseColor(darkVibrant)))


    val scaffoldState = rememberBottomSheetScaffoldState()
    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(targetValue = if (currentSheetFraction == 1f) EXTRA_LARGE_PADDING else 0.dp)

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd = radiusAnim),
        sheetContent = { selectedHero?.let {
            BottomSheetContent(selectedHero = selectedHero,
                               infoBoxIconColor = Color(parseColor(vibrant)),
                               sheetBackgroundColor =  Color(parseColor(darkVibrant)),
                               contentColor =  Color(parseColor(onDarkVibrant))) } },
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        content = {

            selectedHero?.let { it1 ->

                BackgroundContent(
                    heroImage = it1.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor =  Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                    navHostController.popBackStack()
                })
            }.also {
                Spacer(modifier = Modifier.size(0.dp))
            }

        })
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor)
{
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
          ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = LARGE_PADDING),
           verticalAlignment = Alignment.CenterVertically) {

            Icon(
                modifier = Modifier
                    .size(25.dp)
                    .weight(2f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo),
                tint = contentColor)

            Text(
                modifier = Modifier.weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
                )

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MEDIUM_PADDING),
           horizontalArrangement = Arrangement.SpaceBetween) {

            InfoBox(
                icon = painterResource(id = R.drawable.bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = stringResource(R.string.power),
                textColor = contentColor)
            
            InfoBox(
                icon = painterResource(id = R.drawable.calender),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.month),
                textColor = contentColor)

            InfoBox(
                icon = painterResource(id = R.drawable.cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor)
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
            )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINE
            )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
           ) {

            OrderedList(title = stringResource(R.string.family), items = selectedHero.family, textColor = contentColor)

            OrderedList(title = stringResource(R.string.abilities), items = selectedHero.abilities, textColor = contentColor)

            OrderedList(title = stringResource(R.string.nature_type), items = selectedHero.natureType, textColor = contentColor)
        }
    }
}

@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: ()->Unit)
{
    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberImagePainter(data = imageUrl){
        error(R.drawable.placeholder)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + 0.4f)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
             contentScale = ContentScale.Crop)
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
           ) {

            IconButton(modifier = Modifier.padding(all = SMALL_PADDING),
                       onClick = { onCloseClicked() }) {

                Icon(
                    modifier = Modifier.size(CLOSE_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                     contentDescription = stringResource(id = R.string.close_icon),
                    tint = Color.White)
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {

        val fraction = bottomSheetState.progress

        return when
        {
            bottomSheetState.isCollapsed && fraction == 1f -> 1f
            bottomSheetState.isCollapsed && fraction < 1f -> 1f - fraction
            bottomSheetState.isExpanded && fraction == 1f -> 0f
            bottomSheetState.isExpanded && fraction < 1f -> fraction
            else -> fraction
        }
    }

@Preview
@Composable
fun BottomSheetContentPreview()
{
    BottomSheetContent(
        selectedHero = Hero(
            id = 15,
            name = "name15",
            image = "/images/image2.jpg",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n" + "\n",
            rating = 3.0,
            power = 98,
            month = "July",
            day = "23rd",
            family = listOf("Jack", "Roz", "Oliver", "Jack","Roz", "Oliver"),
            abilities = listOf("a1", "a2", "a3"),
            natureType = listOf("n1", "n2", "n3")
                           ))

}
