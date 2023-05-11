package com.mahapp1397.borutoapp.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahapp1397.borutoapp.R
import com.mahapp1397.borutoapp.ui.theme.BorutoAppTheme
import com.mahapp1397.borutoapp.ui.theme.EXTRA_SMALL_PADDING
import com.mahapp1397.borutoapp.ui.theme.SMALL_PADDING
import com.mahapp1397.borutoapp.ui.theme.titleColor

@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color)
{
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING, start = EXTRA_SMALL_PADDING)
                .size(32.dp),
            painter = icon,
            contentDescription = stringResource(R.string.info_icon),
            tint = iconColor)
        
        Column { 
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Black
                )
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.overline.fontSize)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoBoxPreview()
{
    BorutoAppTheme {

        Surface {
            InfoBox(icon = painterResource(id = R.drawable.bolt),
                    iconColor = MaterialTheme.colors.primary,
                    bigText = "99",
                    smallText = "Power",
                    textColor = MaterialTheme.colors.titleColor)
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun InfoBoxDarkPreview()
{
    BorutoAppTheme {

        Surface {
            InfoBox(icon = painterResource(id = R.drawable.bolt),
                    iconColor = MaterialTheme.colors.primary,
                    bigText = "99",
                    smallText = "Power",
                    textColor = MaterialTheme.colors.titleColor)
        }
    }
}