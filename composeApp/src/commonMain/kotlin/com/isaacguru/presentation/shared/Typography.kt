package com.isaacguru.presentation.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import isaacguru.composeapp.generated.resources.Res
import isaacguru.composeapp.generated.resources.upheavtt
import org.jetbrains.compose.resources.Font

@Composable
fun UpHeavFontFamily() =
    FontFamily(
        Font(Res.font.upheavtt),
    )
