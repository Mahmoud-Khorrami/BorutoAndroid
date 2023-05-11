package com.mahapp1397.borutoapp.presentation.comon

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mahapp1397.borutoapp.R
import com.mahapp1397.borutoapp.domain.models.Hero
import com.mahapp1397.borutoapp.navigation.Screen
import com.mahapp1397.borutoapp.presentation.component.RatingWidget
import com.mahapp1397.borutoapp.presentation.component.ShimmerEffect
import com.mahapp1397.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import com.mahapp1397.borutoapp.ui.theme.LARGE_PADDING
import com.mahapp1397.borutoapp.ui.theme.MEDIUM_PADDING
import com.mahapp1397.borutoapp.ui.theme.SMALL_PADDING
import com.mahapp1397.borutoapp.ui.theme.topAppBarContentColor
import com.mahapp1397.borutoapp.utils.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
    heroes : LazyPagingItems<Hero>,
    navHostController: NavHostController)
{
    val result = handlePagingResult(heroes = heroes)

    if (result)
    {
        LazyColumn(contentPadding = PaddingValues(all = SMALL_PADDING),
                   verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)){
            items(items = heroes, key = {hero-> hero.id}){

                it?.let {
                    HeroItem(hero = it, navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(heroes: LazyPagingItems<Hero>): Boolean
{

    heroes.apply {
        val error = when{
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else-> null
        }

        return when{



            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            error != null ->
            {
                EmptyScreen(error = error, heroes = heroes)
                false
            }
            heroes.itemCount < 1 ->
            {
                EmptyScreen()
                false
            }

            else-> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun HeroItem(hero: Hero, navHostController: NavHostController)
{
    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}"){
        placeholder(R.drawable.placeholder)
        error(R.drawable.placeholder)
    }

    Box(modifier = Modifier
        .height(HERO_ITEM_HEIGHT)
        .clickable { navHostController.navigate(Screen.Details.passHeroId(hero.id)) },
       contentAlignment = Alignment.BottomStart) {

        Surface(shape = RoundedCornerShape(LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop)
        }
        
        Surface(modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth(),
               color = Color.Black.copy(alpha = ContentAlpha.medium),
               shape = RoundedCornerShape(bottomEnd = LARGE_PADDING, bottomStart = LARGE_PADDING)
               ) {
            
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(all = MEDIUM_PADDING)) {

                Text(
                    text = hero.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis)
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                   ) {
                    RatingWidget(modifier = Modifier.padding(SMALL_PADDING),
                                 rating = hero.rating)
                    Text(
                        text = "${hero.rating}",
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        textAlign = TextAlign.Center)

                }
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun HeroItemPreview()
{ 
    HeroItem(hero = Hero(
        id = 13,
        name = "name13",
        image = "/images/image1.jpg",
        about = "about1",
        rating = 5.0,
        power = 98,
        month = "July",
        day = "23rd",
        family = listOf("f1", "f2", "f3"),
        abilities = listOf("a1", "a2", "a3"),
        natureType = listOf("n1", "n2", "n3"))
             , navHostController = rememberNavController())
}

@ExperimentalCoilApi
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HeroItemDarkPreview()
{
    HeroItem(hero = Hero(
        id = 13,
        name = "name13",
        image = "/images/image1.jpg",
        about = "about1",
        rating = 5.0,
        power = 98,
        month = "July",
        day = "23rd",
        family = listOf("f1", "f2", "f3"),
        abilities = listOf("a1", "a2", "a3"),
        natureType = listOf("n1", "n2", "n3"))
             , navHostController = rememberNavController())
}