package com.isaacguru.presentation.features.inventory.items.model

import androidx.compose.ui.text.AnnotatedString
import com.isaacguru.domain.GameAspect
import com.isaacguru.presentation.shared.fromDiscordString

data class ViewItemWrapper<T : GameAspect>(val raw: T, val richDescription: AnnotatedString)

fun <T : GameAspect> viewWrapperOf(item: T) =
    ViewItemWrapper(raw = item, richDescription = item.description.fromDiscordString())
