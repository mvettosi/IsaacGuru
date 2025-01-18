package com.isaacguru.presentation.features.inventory.items.model

import androidx.compose.ui.text.AnnotatedString
import com.isaacguru.domain.GameAspect
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.presentation.shared.fromDiscordString
import com.isaacguru.presentation.shared.fromPools

data class ViewItemWrapper<T : GameAspect>(
    val raw: T,
    val richPools: AnnotatedString?,
    val richDescription: AnnotatedString,
    val subtitle: String? = null
)

fun <T : GameAspect> viewWrapperOf(item: T) =
    ViewItemWrapper(
        raw = item,
        richPools = (item as? Item)?.itemPools?.fromPools(),
        richDescription = item.description.fromDiscordString(),
        subtitle = if (item is Item.Active) "Active Item" else "Passive Item")
