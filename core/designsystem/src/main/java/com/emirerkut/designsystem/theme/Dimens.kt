package com.emirerkut.designsystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val cardWidthM: Dp,
    val cardHeightM: Dp,
    val cardWidthS: Dp,
    val cardHeightS: Dp,
    val genericL: Dp,
    val genericM: Dp,
    val genericS: Dp,
    val genericXS: Dp,
    val genericXXS: Dp,
    val genericEmpty: Dp,
) {
    companion object {
        val default = Dimens(
            cardWidthM = 150.dp,
            cardHeightM = 200.dp,
            cardWidthS = 100.dp,
            cardHeightS = 150.dp,
            genericL = 32.dp,
            genericM = 16.dp,
            genericS = 8.dp,
            genericXS = 4.dp,
            genericXXS = 2.dp,
            genericEmpty = 0.dp,
        )
    }
}
