package com.isaacguru.presentation.shared

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

// General
val SeparatorColor = Color("#8c0008".toColorInt())

// Item Details
val BottomBarBackgroundColor = Color("#141414".toColorInt())
val TopBarBackgroundColor = Color("#333333".toColorInt())
val TopBarFocusedBackgroundColor = Color("#444444".toColorInt())
val TopBarSearchBackgroundColor = Color("#3b3b3b".toColorInt())
val ItemDetailsBackgroundColor = Color(red = 51f, green = 51f, blue = 51f, alpha = 0.05f)
val QuoteColor = Color("#0089FF".toColorInt())
val Divider = Color("#b6b4b4".toColorInt())
val ItemIdColor = Color("#3f51b5".toColorInt())
val TrinketIdColor = Color("#609164".toColorInt())
val PickupIdColor = Color("#ada447".toColorInt())
val ConsumableIdColor = Color("#bf4f4f".toColorInt())
val MachineIdColor = Color("#916e44".toColorInt())
val CharacterIdColor = Color("#946dad".toColorInt())
val TransformationIdColor = Color("#ef4747".toColorInt())
val CurseIdColor = Color("#8d5dab".toColorInt())
val AppRed = Color(red = 220, green = 53, blue = 69)
val ClearSearchRed = Color(red = 140, green = 0, blue = 8)

object FilterOptionColor {
  val Default = Color(red = 108, green = 117, blue = 125)
  val Green = Color(red = 25, green = 135, blue = 84)
  val Yellow = Color(red = 255, green = 193, blue = 7)
  val Cyan = Color(red = 13, green = 202, blue = 240)
  val Blue = Color(red = 13, green = 110, blue = 253)
  val Red = Color(red = 220, green = 53, blue = 69)
  val White = Color(red = 248, green = 249, blue = 250)
}

// Color Scheme
val primaryDark = Color(0xFFD3BCFD)
val onPrimaryDark = Color(0xFF38265C)
val primaryContainerDark = Color(0xFF4F3D74)
val onPrimaryContainerDark = Color(0xFFEBDDFF)
val secondaryDark = Color(0xFFCDC2DB)
val onSecondaryDark = Color(0xFF342D40)
val secondaryContainerDark = Color(0xFF4B4358)
val onSecondaryContainerDark = Color(0xFFE9DEF8)
val tertiaryDark = Color(0xFFF0B7C5)
val onTertiaryDark = Color(0xFF4A2530)
val tertiaryContainerDark = Color(0xFF643B46)
val onTertiaryContainerDark = Color(0xFFFFD9E1)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF151218)
val onBackgroundDark = Color(0xFFE7E0E8)
val surfaceDark = Color(0xFF151218)
val onSurfaceDark = Color(0xFFE7E0E8)
val surfaceVariantDark = Color(0xFF49454E)
val onSurfaceVariantDark = Color(0xFFCBC4CF)
val outlineDark = Color(0xFF948F99)
val outlineVariantDark = Color(0xFF49454E)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFE7E0E8)
val inverseOnSurfaceDark = Color(0xFF322F35)
val inversePrimaryDark = Color(0xFF68548E)
val surfaceDimDark = Color(0xFF151218)
val surfaceBrightDark = Color(0xFF3B383E)
val surfaceContainerLowestDark = Color(0xFF0F0D13)
val surfaceContainerLowDark = Color(0xFF1D1B20)
val surfaceContainerDark = Color(0xFF211F24)
val surfaceContainerHighDark = Color(0xFF2C292F)
val surfaceContainerHighestDark = Color(0xFF36343A)

val appColorScheme =
    darkColorScheme(
        primary = primaryDark,
        onPrimary = onPrimaryDark,
        primaryContainer = primaryContainerDark,
        onPrimaryContainer = onPrimaryContainerDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        secondaryContainer = secondaryContainerDark,
        onSecondaryContainer = onSecondaryContainerDark,
        tertiary = tertiaryDark,
        onTertiary = onTertiaryDark,
        tertiaryContainer = tertiaryContainerDark,
        onTertiaryContainer = onTertiaryContainerDark,
        error = errorDark,
        onError = onErrorDark,
        errorContainer = errorContainerDark,
        onErrorContainer = onErrorContainerDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surface = surfaceDark,
        onSurface = onSurfaceDark,
        surfaceVariant = surfaceVariantDark,
        onSurfaceVariant = onSurfaceVariantDark,
        outline = outlineDark,
        outlineVariant = outlineVariantDark,
        scrim = scrimDark,
        inverseSurface = inverseSurfaceDark,
        inverseOnSurface = inverseOnSurfaceDark,
        inversePrimary = inversePrimaryDark,
        surfaceDim = surfaceDimDark,
        surfaceBright = surfaceBrightDark,
        surfaceContainerLowest = surfaceContainerLowestDark,
        surfaceContainerLow = surfaceContainerLowDark,
        surfaceContainer = surfaceContainerDark,
        surfaceContainerHigh = surfaceContainerHighDark,
        surfaceContainerHighest = surfaceContainerHighestDark,
    )
