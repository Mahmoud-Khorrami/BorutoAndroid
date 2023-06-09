package com.mahapp1397.borutoapp.presentation.comon

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mahapp1397.borutoapp.R
import com.mahapp1397.borutoapp.domain.models.Hero
import com.mahapp1397.borutoapp.ui.theme.DarkGray
import com.mahapp1397.borutoapp.ui.theme.LightGray
import com.mahapp1397.borutoapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.mahapp1397.borutoapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null)
{
    var message by remember {
        mutableStateOf("Find Your favorite heroes")
    }

    var icon by remember {
        mutableStateOf(R.drawable.search_doc)
    }

    if (error != null)
    {
        message = parsErrorMessage(error =  error)
        icon = R.drawable.error
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(durationMillis = 1000))

    LaunchedEffect(key1 = true){startAnimation = true}

    EmptyContent(alphaAnim = alphaAnim,
                 icon = icon,
                 message = message,
                 heroes = heroes,
                 error = error)

}

@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    heroes: LazyPagingItems<Hero>? = null,
    error: LoadState.Error? = null,)
{
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
             isRefreshing = true
             heroes?.refresh()
             isRefreshing = false })
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
              ) {

            Icon(modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT)
                .alpha(alphaAnim),
                 painter = painterResource(id = icon),
                 contentDescription = stringResource(R.string.network_error_icon),
                 tint = if (isSystemInDarkTheme()) LightGray else DarkGray)

            Text(
                text = message,
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alphaAnim),
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize)
        }
    }

}

fun parsErrorMessage(error: LoadState.Error): String
{
    return when(error.error){
        is SocketTimeoutException -> "Server Unavailable"

        is ConnectException-> "Internet Unavailable"

        else-> "Unknown Error"
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview()
{ 
    EmptyContent(alphaAnim = ContentAlpha.disabled, icon = R.drawable.error, message = "unknown error")
}