package com.mahapp1397.borutoapp.domain.models

import androidx.annotation.DrawableRes
import com.mahapp1397.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String)
{
    object First: OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greeting",
        description = "fdfd dgrg vdgrgkgn vdkk vdgkjgkdvkd fkfdkjfdknvk kkdkdvkdkd")

    object Second: OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "fdfd dgrg vdgrgkgn vdkk vdgkjgkdvkd fkfdkjfdknvk kkdkdvkdkd")

    object Third: OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "fdfd dgrg vdgrgkgn vdkk vdgkjgkdvkd fkfdkjfdknvk kkdkdvkdkd")
}
